DataConverter
==

This mod completely rewrites the dataconverter system for Minecraft.
Please note that this fabric mod is not to be used. It is published
and maintained here for the sole purpose of being able to update
to snapshot versions. By updating to snapshot versions, more testing
can be done throughout the update process, and diffs between
versions can be tracked more easily.

This mod will never have a released version. Above everything else
I want worlds to convert correctly, so I cannot publish this mod in
good conscience. This mod does not account for datafixers registered 
by other mods. When you use this mod, you completely accept the risk 
that dataconverters _WILL NOT RUN_ on your mods' data. Your world 
data _WILL BECOME CORRUPT_ as a result, and it is entirely **YOUR FAULT**.

If you want to use this mod, please use [Paper](https://github.com/PaperMC/Paper).
Because plugins cannot register datafixers with DFU, there is no risk
of non-vanilla datafixers being skipped. Plugins that run things through
DFU are not affected, because this mod only redirects Vanilla calls to
DFU to use the new converter system - so it does not affect DFU. It just
doesn't use it.


# Technical overview

### DFU Schema

DFU uses Schema's to define data layouts for types. They don't define all fields 
in the type, just the parts that need to be looked at for conversion. For example, 
here is the schema specification for Enderman (V100, 15w32a):
```java
        schema.register(map, "Enderman", (string) -> {
            return DSL.optionalFields("carried", References.BLOCK_NAME.in(schema), equipment(schema));
        });
```

This specifies that the root CompoundTag for Enderman contains a `BLOCK_NAME` at path "carried."
It should be obvious that Schemas tell DFU's type system where to look if a datafixer wants to convert
BLOCK_NAME in this case. 
More complicated schemas exist, for example (V100 again):
```java
        schema.registerType(false, References.STRUCTURE, () -> {
            return DSL.optionalFields(
                "entities", DSL.list(
                    DSL.optionalFields("nbt", References.ENTITY_TREE.in(schema))
                ), 
                "blocks", DSL.list(
                    DSL.optionalFields("nbt", References.BLOCK_ENTITY.in(schema))
                ), 
                "palette", DSL.list(References.BLOCK_STATE.in(schema))
            );
        });
```

This schema specifies that the root tag of `STRUCTURE` contains 3 paths: `entities`, `blocks`, and `palette`.

In the `entities` path, it specifies that it's a List and that the list contains fields, so it is typically
a `CompoundTag`. This `CompoundTag` contains a field called "nbt", and the value represents an `ENTITY_TREE`.

The `blocks` path is similar to the `entities` path, except its "nbt" field represents a `BLOCK_ENTITY`, not
an `ENTITY_TREE`.

Finally, the `palette` field represents a list _of_ `BLOCK_STATE`.

This aspect of DFU is the cleanest, and allows Mojang to define types easily and reliably - datafixers
just need to define what type they want to modify, and the type system of DFU will navigate to the
types and run them through the datafixer.

### DataConverter DataWalker

Like the DFU Schema, the DataWalker is designed to specify the data layout of types for
dataconverters. However, the DataWalker's responsibly isn't to lay out the data - it's to
actually _run_ the converters. For example, take the schemas above, here are the DataWalker
implementations:

```java
        MCTypeRegistry.ENTITY.addWalker(VERSION, "Enderman", (data, fromVersion, toVersion) -> {
            WalkerUtils.convert(MCTypeRegistry.BLOCK_NAME, data, "carried", fromVersion, toVersion);

            // only return something if we want the root tag to change, but we don't - so ret null. Don't worry about this,
            // no DataWalker is actually recommended to do this.
            return null;
        });
```

As you can see, the DataWalker is simply a piece of code that calls converters.

For the more complicated Schema:

```java
        MCTypeRegistry.STRUCTURE.addStructureWalker(VERSION, (data, fromVersion, toVersion) -> {
            final ListType entities = data.getList("entities", ObjectType.MAP);
            if (entities != null) {
                for (int i = 0, len = entities.size(); i < len; ++i) {
                    WalkerUtils.convert(MCTypeRegistry.ENTITY, entities.getMap(i), "nbt", fromVersion, toVersion);
                }
            }

            final ListType blocks = data.getList("blocks", ObjectType.MAP);
            if (blocks != null) {
                for (int i = 0, len = blocks.size(); i < len; ++i) {
                    WalkerUtils.convert(MCTypeRegistry.TILE_ENTITY, blocks.getMap(i), "nbt", fromVersion, toVersion);
                }
            }

            WalkerUtils.convertList(MCTypeRegistry.BLOCK_STATE, data, "palette", fromVersion, toVersion);

            // only return something if we want the root tag to change, but we don't - so ret null. Don't worry about this,
            // no DataWalker is actually recommended to do this.
            return null;
        });
```

There are no helper functions for converting a single field inside a list, so the list must be iterated over
manually. However, as you can see for the `palette` converter, there is a helper function for converting
lists of just one data type.


While DFU Schema and DataWalker are fundamentally different ways of performing data conversion for subtypes,
they will both effectively end up doing the same thing. They are both designed to simply run converters
on types contained within another type. However, DataWalker is much faster because it does _not_ depend 
on an extremely large type system backend to the traversing for it - it does the traversing itself.
Without the DFU type system, the vast majority of performance overhead and complexity has been eliminated
already. 

### DFU DataFix

DataFix is the overall class responsible for making modifications to data. This is where the actual
_conversion_ process takes place. Because the DataFix classes can get extremely complicated, I'm only 
going to show a simple DataFix class (for V109, 15w33a):

```java
public class EntityHealthFix extends DataFix {
    private static final Set<String> ENTITIES = ...; // unused set of entities with Health


    public EntityHealthFix(Schema schema, boolean changesType) {
        // schema specifies the version
        super(schema, changesType);
    }

    public Dynamic<?> fixTag(Dynamic<?> entityRoot) {
        // while the variable names say float and int, really the type is `Number` - it can be
        // any Number. But I've named them according to what we _expect_ them to be, as that's
        // very important to the datafix here.
        Optional<Number> healthFloat = entityRoot.get("HealF").asNumber().result();
        Optional<Number> healthInt = entityRoot.get("Health").asNumber().result();
        float newHealth;
        if (healthFloat.isPresent()) {
            newHealth = ((Number)healthFloat.get()).floatValue();
            entityRoot = entityRoot.remove("HealF");
        } else {
            if (!healthInt.isPresent()) {
                return entityRoot;
            }

            newHealth = ((Number)healthInt.get()).floatValue();
        }

        return entityRoot.set("Health", entityRoot.createFloat(newHealth));
    }

    public TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("EntityHealthFix", this.getInputSchema().getType(References.ENTITY), (typed) -> {
            return typed.update(DSL.remainderFinder(), this::fixTag);
        });
    }
}
```

The converter is fairly straightforward - update the Health tag to be a float. If `HealF` exists, then use
that - else, try to use the `Health` tag. If none exist, do nothing.

The makeRule() method is there to tell DFU it wants to modify all `ENTITY` types.

Something you need to note is that Dynamics are Copy-On-Write (they do SHALLOW copies, not DEEP). This is 
why you will see lines like this:
```java
            entityRoot = entityRoot.remove("HealF");
```
You will see in a moment that DataConverter is _not_ Copy-On-Write. This is something very important
that you need to keep in mind if you want to look at both DataConverter's converters and DFU's.


### DataConverter

DataConverters are going to the same job of DataFix. Take an input data, do converting, and return 
an output data.
Here's the converter for the health fix:
```java
        // version must be specified to the DataConverter
        MCTypeRegistry.ENTITY.addStructureConverter(new DataConverter<>(VERSION) {
            // versions are provided in the convert method. Not used much, but there just in case.
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final Number healF = data.getNumber("HealF");
                final Number heal = data.getNumber("Health");

                final float newHealth;

                if (healF != null) {
                    data.remove("HealF");
                    newHealth = healF.floatValue();
                } else {
                    if (heal == null) {
                        return null;
                    }

                    newHealth = heal.floatValue();
                }

                data.setFloat("Health", newHealth);

                // null once again indicates we have no need to change the root tag. Rarely is this ever needed,
                // but sometimes it is. See V135's passenger fix - it needs to change root tag because the Riding
                // entities are swapped with passengers.
                return null;
            }
        });
```

You will notice that no Optionals have been used. Null is to indicate when values do not exist (or when
the type is not as requested).

The code does basically the same thing. However, it uses MapType instead of Dynamic for reading and writing to
the underlying `CompoundTag`. Why not just write to `CompoundTag` directly? Technically I also need to support
read/write operations to JSON data (see ADVANCEMENTS type). So the MapType is an abstraction.

Performance impact is low since operations are not Copy-On-Write and do not go through a type system.
DataConverters are simple enough that no optimising is really needed, because the performance problem
of DFU simply doesn't exist: its type system. In fact, the only DataConverter I ever optimised was the 
chunk flatten converter (DataConverterFlattenChunk).

DataConverters tend to stay simple because there is no type system to deal with at all, so all the complexity
comes from the logical data changes occurring. This makes debugging them easy. For example, take a look
at the MinecartSpawner Schema/DataWalker (V99, pre-converters):

DataWalker:
```java
        // Yes, two walkers are allowed: but only for the same version. Later versions need to redefine
        // them all, if they're needed.
        MCTypeRegistry.ENTITY.addWalker(VERSION, "MinecartSpawner", new DataWalkerBlockNames("DisplayTile"));
        MCTypeRegistry.ENTITY.addWalker(VERSION, "MinecartSpawner", MCTypeRegistry.UNTAGGED_SPAWNER::convert);
```

Schema:
```java
        schema.register(map, "MinecartSpawner", () -> {
            return DSL.optionalFields("DisplayTile", References.BLOCK_NAME.in(schema), References.UNTAGGED_SPAWNER.in(schema));
        });
```

They look the same right? Well they are. It turns out, the root tag of MinecartSpawner is also an `UNTAGGED_SPAWNER`.
While this is completely acceptable in DataConverter, because it's just going to run convert(), DFU chokes a bit.

Well what happens if you shove a MinecartSpawner through DFU?

This.
<details>
<summary>Complete mess</summary>

class_3602 -> EntityHorseSplitFix

class_1167 -> EntityTransformFix

Yup that's right, this stacktrace doesn't even point to any DataFix that even _touches_ Minecarts or 
Spawners. It doesn't even point anywhere near one. So good luck figuring that one out from the stacktrace.
I figured this out only by curious inspection, and only wanted to see if DFU could even handle it.

Imagine this happening to some data you actually care about though. You cannot debug it. 
I remember during 1.16 when Paper was trying to fix massive lag problems caused by errors in DFU.
It took basically _5_ or so people to cobble together a solution, and that solution was _total trash_. No 
offense to anyone involved (I was involved), but that's just the best we could've done with DFU. An issue 
occurs, and you just have to pray that one of the few people who understand this system can do something 
about it.

```text
java.lang.IllegalArgumentException: Couldn't upcast
	at com.mojang.datafixers.TypedOptic.lambda$apply$0(TypedOptic.java:60) ~[datafixerupper-4.0.26.jar:?]
	at java.util.Optional.orElseThrow(Optional.java:403) ~[?:?]
	at com.mojang.datafixers.TypedOptic.apply(TypedOptic.java:59) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.Typed.get(Typed.java:48) ~[datafixerupper-4.0.26.jar:?]
	at net.minecraft.class_3602.method_4982(class_3602.java:19) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.class_1167.method_4984(class_1167.java:30) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at com.mojang.datafixers.FunctionType$Instance.lambda$first$1(FunctionType.java:81) ~[datafixerupper-4.0.26.jar:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at com.mojang.datafixers.util.Either.lambda$mapRight$1(Either.java:166) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either$Right.map(Either.java:99) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either.mapRight(Either.java:166) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.FunctionType$Instance.lambda$right$6(FunctionType.java:104) ~[datafixerupper-4.0.26.jar:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at com.mojang.datafixers.util.Either.lambda$mapRight$1(Either.java:166) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either$Right.map(Either.java:99) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either.mapRight(Either.java:166) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.FunctionType$Instance.lambda$right$6(FunctionType.java:104) ~[datafixerupper-4.0.26.jar:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at com.mojang.datafixers.util.Either.lambda$mapRight$1(Either.java:166) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either$Right.map(Either.java:99) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either.mapRight(Either.java:166) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.FunctionType$Instance.lambda$right$6(FunctionType.java:104) ~[datafixerupper-4.0.26.jar:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at com.mojang.datafixers.util.Either.lambda$mapRight$1(Either.java:166) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either$Right.map(Either.java:99) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either.mapRight(Either.java:166) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.FunctionType$Instance.lambda$right$6(FunctionType.java:104) ~[datafixerupper-4.0.26.jar:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at com.mojang.datafixers.util.Either.lambda$mapRight$1(Either.java:166) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either$Right.map(Either.java:99) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either.mapRight(Either.java:166) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.FunctionType$Instance.lambda$right$6(FunctionType.java:104) ~[datafixerupper-4.0.26.jar:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Fold.lambda$null$2(Fold.java:48) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.FunctionType$Instance.lambda$first$1(FunctionType.java:81) ~[datafixerupper-4.0.26.jar:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at com.mojang.datafixers.FunctionType$Instance.lambda$first$1(FunctionType.java:81) ~[datafixerupper-4.0.26.jar:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at com.mojang.datafixers.util.Either.lambda$mapRight$1(Either.java:166) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either$Right.map(Either.java:99) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either.mapRight(Either.java:166) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.FunctionType$Instance.lambda$right$6(FunctionType.java:104) ~[datafixerupper-4.0.26.jar:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either.lambda$mapRight$1(Either.java:166) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either$Right.map(Either.java:99) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either.mapRight(Either.java:166) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.FunctionType$Instance.lambda$right$6(FunctionType.java:104) ~[datafixerupper-4.0.26.jar:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Fold.lambda$null$2(Fold.java:48) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.FunctionType$Instance.lambda$null$3(FunctionType.java:93) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.optics.ListTraversal.lambda$wander$0(ListTraversal.java:19) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.FunctionType$Instance.lambda$wander$4(FunctionType.java:94) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either.lambda$mapRight$1(Either.java:166) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either$Right.map(Either.java:99) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either.mapRight(Either.java:166) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.FunctionType$Instance.lambda$right$6(FunctionType.java:104) ~[datafixerupper-4.0.26.jar:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at com.mojang.datafixers.FunctionType$Instance.lambda$first$1(FunctionType.java:81) ~[datafixerupper-4.0.26.jar:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.FunctionType$Instance.lambda$first$1(FunctionType.java:81) ~[datafixerupper-4.0.26.jar:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at com.mojang.datafixers.FunctionType$Instance.lambda$first$1(FunctionType.java:81) ~[datafixerupper-4.0.26.jar:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at java.util.function.Function.lambda$compose$0(Function.java:68) ~[?:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.functions.Comp.lambda$null$5(Comp.java:69) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.types.Type.capWrite(Type.java:167) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.types.Type.lambda$readAndWrite$9(Type.java:159) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.serialization.DataResult.lambda$flatMap$10(DataResult.java:138) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.util.Either$Left.map(Either.java:38) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.serialization.DataResult.flatMap(DataResult.java:136) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.types.Type.readAndWrite(Type.java:158) ~[datafixerupper-4.0.26.jar:?]
	at com.mojang.datafixers.DataFixerUpper.update(DataFixerUpper.java:84) ~[datafixerupper-4.0.26.jar:?]
	at net.minecraft.class_2512.method_10693(class_2512.java:466) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.class_3977.method_17907(class_3977.java:37) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.class_3898.method_17979(class_3898.java:863) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.class_3898.method_17256(class_3898.java:520) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at java.util.concurrent.CompletableFuture$AsyncSupply.run(CompletableFuture.java:1764) ~[?:?]
	at net.minecraft.class_1255.method_18859(class_1255.java:144) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.class_3215$class_4212.method_18859(class_3215.java:545) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.class_1255.method_16075(class_1255.java:118) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.class_3215$class_4212.method_16075(class_3215.java:554) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.class_3215.method_19492(class_3215.java:280) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.server.MinecraftServer.method_20415(MinecraftServer.java:749) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.server.MinecraftServer.method_16075(MinecraftServer.java:737) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.class_1255.method_18857(class_1255.java:127) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.server.MinecraftServer.method_16208(MinecraftServer.java:722) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.server.MinecraftServer.method_3774(MinecraftServer.java:505) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.server.MinecraftServer.method_3735(MinecraftServer.java:338) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.class_1132.method_3823(class_1132.java:67) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.server.MinecraftServer.method_29741(MinecraftServer.java:645) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at net.minecraft.server.MinecraftServer.method_29739(MinecraftServer.java:257) ~[intermediary-fabric-loader-0.11.3-1.16.5.jar:?]
	at java.lang.Thread.run(Thread.java:831) [?:?]
```
</details>

TL;DR big stacktrace gives only misleading information.

This concludes the general technical overview. If you want to contribute, you should first
take a look at a variety of DataFix's I have ported over. There you can see how I handled
complicated DataFix's and very basic ones (like simple item/block/entity renames) and how I
expect converters to be laid out. You can start at `MCTypeRegistry` - this is where all converters
and walkers are registered.


## Comparison

### Bugs fixed
- Minecart Spawner's do not fail to convert for pre converter data.
- Flower pot items convert correctly (there were _several_ problems...)
- Fix logs like `Unable to resolve BlockEntity for ItemStack` - Mojang did not specify the full
  Item name -> Block Entity map. I have code that will ensure the map includes everything.
- Fix incorrect potion conversion from ancient versions (pre converters). Not sure why DFU breaks here...
- Tamed wolf collar colours are not managled during the Flattening conversion
- Incorrect handling of modern entity items that have entity NBT contained
 within them (spawn eggs, item frames)

### Known bugs
This data converter is new. Of course there are going to be bugs. Please take backups before using, 
and if you find problems you need to open a report with the relevant logs and world data. And then
they will actually get fixed, because this system is actually debuggable.

As time goes on, this converter will become more reliable than DFU since this converter is more easily
debugged. So fixing things is actually practically possible.


### Performance
The new data converter is at minimum 30 times faster than DFU for converting freshly generated 1.7.10 worlds
to 1.17 (this runs through all data converters, so it's a solid test).
When chunks contain a lot of data (like shulkers with lots of items), the converter can be up to 200 times faster.

This is fast enough in my testing to completely obsolete the usage of Force Upgrading. I tested force upgrading
this world:
[Realm of Midgard v30](https://www.curseforge.com/minecraft/worlds/seven-hills/files/2580365)

My SSD is a Samsung 970 EVO 1TB (NVMe). So the Disk I/O should be minimized.
Conversion process broke down like this:
- ~75% was spent on reading/writing the chunk data (this INCLUDES decompression/compression)
- ~25% was spent _converting_ the data

So basically, the vast majority of time is spent on read/write. Why bother force upgrading? 
Literally a waste of your time with DataConverter.

## Conclusion
New converter system makes force upgrading _obsolete_, that's how fast it is. New converter system is new,
so please do backups before using - or else you put your world data at risk.
