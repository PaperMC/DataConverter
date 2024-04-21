package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.advancements.ConverterCriteriaRename;
import ca.spottedleaf.dataconverter.minecraft.converters.entity.ConverterEntityToVariant;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

import java.util.Map;

public final class V3086 {

    private static final int VERSION = MCVersions.V22W13A + 1;

    private static final Int2ObjectOpenHashMap<String> CAT_ID_CONVERSION = new Int2ObjectOpenHashMap<>();
    static {
        CAT_ID_CONVERSION.defaultReturnValue("minecraft:tabby");
        CAT_ID_CONVERSION.put(0, "minecraft:tabby");
        CAT_ID_CONVERSION.put(1, "minecraft:black");
        CAT_ID_CONVERSION.put(2, "minecraft:red");
        CAT_ID_CONVERSION.put(3, "minecraft:siamese");
        CAT_ID_CONVERSION.put(4, "minecraft:british");
        CAT_ID_CONVERSION.put(5, "minecraft:calico");
        CAT_ID_CONVERSION.put(6, "minecraft:persian");
        CAT_ID_CONVERSION.put(7, "minecraft:ragdoll");
        CAT_ID_CONVERSION.put(8, "minecraft:white");
        CAT_ID_CONVERSION.put(9, "minecraft:jellie");
        CAT_ID_CONVERSION.put(10, "minecraft:all_black");
    }

    private static final Map<String, String> CAT_ADVANCEMENTS_CONVERSION = Map.ofEntries(
                    Map.entry("textures/entity/cat/tabby.png", "minecraft:tabby"),
                    Map.entry("textures/entity/cat/black.png", "minecraft:black"),
                    Map.entry("textures/entity/cat/red.png", "minecraft:red"),
                    Map.entry("textures/entity/cat/siamese.png", "minecraft:siamese"),
                    Map.entry("textures/entity/cat/british_shorthair.png", "minecraft:british"),
                    Map.entry("textures/entity/cat/calico.png", "minecraft:calico"),
                    Map.entry("textures/entity/cat/persian.png", "minecraft:persian"),
                    Map.entry("textures/entity/cat/ragdoll.png", "minecraft:ragdoll"),
                    Map.entry("textures/entity/cat/white.png", "minecraft:white"),
                    Map.entry("textures/entity/cat/jellie.png", "minecraft:jellie"),
                    Map.entry("textures/entity/cat/all_black.png", "minecraft:all_black")
    );

    public static void register() {
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:cat", new ConverterEntityToVariant(VERSION, "CatType", CAT_ID_CONVERSION::get));
        MCTypeRegistry.ADVANCEMENTS.addStructureConverter(new ConverterCriteriaRename(VERSION, "minecraft:husbandry/complete_catalogue", CAT_ADVANCEMENTS_CONVERSION::get));
    }

    private V3086() {}
}
