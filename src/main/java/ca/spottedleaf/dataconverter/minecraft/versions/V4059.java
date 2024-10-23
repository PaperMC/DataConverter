package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.converters.datatypes.DataWalker;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.walkers.generic.WalkerUtils;
import ca.spottedleaf.dataconverter.types.ListType;
import ca.spottedleaf.dataconverter.types.MapType;
import ca.spottedleaf.dataconverter.types.ObjectType;
import ca.spottedleaf.dataconverter.types.TypeUtil;

public final class V4059 {

    private static final int VERSION = MCVersions.V24W33A + 1;

    public static void register() {
        // previous version: 3818.3
        MCTypeRegistry.DATA_COMPONENTS.addStructureWalker(VERSION, new DataWalker<>() {
            private static void walkBlockPredicates(final MapType<String> root, final long fromVersion, final long toVersion) {
                if (root.hasKey("blocks", ObjectType.STRING)) {
                    WalkerUtils.convert(MCTypeRegistry.BLOCK_NAME, root, "blocks", fromVersion, toVersion);
                } else if (root.hasKey("blocks", ObjectType.LIST)) {
                    WalkerUtils.convertList(MCTypeRegistry.BLOCK_NAME, root, "blocks", fromVersion, toVersion);
                }
            }

            @Override
            public MapType<String> walk(final MapType<String> root, final long fromVersion, final long toVersion) {
                WalkerUtils.convertListPath(MCTypeRegistry.ENTITY, root, "minecraft:bees", "entity_data", fromVersion, toVersion);

                WalkerUtils.convert(MCTypeRegistry.TILE_ENTITY, root, "minecraft:block_entity_data", fromVersion, toVersion);
                WalkerUtils.convertList(MCTypeRegistry.ITEM_STACK, root, "minecraft:bundle_contents", fromVersion, toVersion);

                final MapType<String> canBreak = root.getMap("minecraft:can_break");
                if (canBreak != null) {
                    final ListType predicates = canBreak.getList("predicates", ObjectType.MAP);
                    if (predicates != null) {
                        for (int i = 0, len = predicates.size(); i < len; ++i) {
                            walkBlockPredicates(predicates.getMap(i), fromVersion, toVersion);
                        }
                    }
                    // Not handled by DFU: simple encoding does not require "predicates"
                    walkBlockPredicates(canBreak, fromVersion, toVersion);
                }

                final MapType<String> canPlaceOn = root.getMap("minecraft:can_place_on");
                if (canPlaceOn != null) {
                    final ListType predicates = canPlaceOn.getList("predicates", ObjectType.MAP);
                    if (predicates != null) {
                        for (int i = 0, len = predicates.size(); i < len; ++i) {
                            walkBlockPredicates(predicates.getMap(i), fromVersion, toVersion);
                        }
                    }
                    // Not handled by DFU: simple encoding does not require "predicates"
                    walkBlockPredicates(canPlaceOn, fromVersion, toVersion);
                }

                WalkerUtils.convertList(MCTypeRegistry.ITEM_STACK, root, "minecraft:charged_projectiles", fromVersion, toVersion);
                WalkerUtils.convertListPath(MCTypeRegistry.ITEM_STACK, root, "minecraft:container", "item", fromVersion, toVersion);
                WalkerUtils.convert(MCTypeRegistry.ENTITY, root, "minecraft:entity_data", fromVersion, toVersion);
                WalkerUtils.convertList(MCTypeRegistry.ITEM_NAME, root, "minecraft:pot_decorations", fromVersion, toVersion);
                WalkerUtils.convert(MCTypeRegistry.ITEM_STACK, root, "minecraft:use_remainder", fromVersion, toVersion);

                final MapType<String> equippable = root.getMap("minecraft:equippable");
                if (equippable != null) {
                    WalkerUtils.convert(MCTypeRegistry.ENTITY_NAME, equippable, "allowed_entities", fromVersion, toVersion);
                    WalkerUtils.convertList(MCTypeRegistry.ENTITY_NAME, equippable, "allowed_entities", fromVersion, toVersion);
                }

                return null;
            }
        });

        MCTypeRegistry.DATA_COMPONENTS.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final MapType<String> food = data.getMap("minecraft:food");
                if (food == null) {
                    return null;
                }

                final TypeUtil typeUtil = data.getTypeUtil();

                final float eatSeconds = food.getFloat("eat_seconds", 1.6F);

                final ListType oldEffects = food.getList("effects", ObjectType.MAP);
                final ListType newEffects = typeUtil.createEmptyList();
                if (oldEffects != null) {
                    for (int i = 0, len = oldEffects.size(); i < len; ++i) {
                        final MapType<String> oldEffect = oldEffects.getMap(i);

                        final MapType<String> newEffect = typeUtil.createEmptyMap();
                        newEffects.addMap(newEffect);

                        newEffect.setString("type", "minecraft:apply_effects");

                        final Object oldEffectEffect = oldEffect.getGeneric("effect");
                        final ListType newEffectEffects = typeUtil.createEmptyList();
                        newEffectEffects.addGeneric(oldEffectEffect);
                        newEffect.setList("effects", newEffectEffects);

                        newEffect.setFloat("probability", oldEffect.getFloat("probability", 1.0F));
                    }
                }

                final Object convertsTo = food.getGeneric("using_converts_to");
                if (convertsTo != null) {
                    data.setGeneric("minecraft:use_remainder", convertsTo);
                }

                food.remove("eat_seconds");
                food.remove("effects");
                food.remove("using_converts_to");

                final MapType<String> consumable = typeUtil.createEmptyMap();
                data.setMap("minecraft:consumable", consumable);

                consumable.setFloat("consume_seconds", eatSeconds);
                consumable.setList("on_consume_effects", newEffects);

                return null;
            }
        });
    }

    private V4059() {}
}
