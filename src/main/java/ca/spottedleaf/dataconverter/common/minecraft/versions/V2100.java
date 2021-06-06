package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.minecraft.walkers.generic.WalkerUtils;
import ca.spottedleaf.dataconverter.common.minecraft.walkers.itemstack.DataWalkerItemLists;
import ca.spottedleaf.dataconverter.common.types.ListType;
import ca.spottedleaf.dataconverter.common.types.ObjectType;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

public final class V2100 {

    protected static final int VERSION = MCVersions.V1_14_4 + 124;
    protected static final Map<String, String> RECIPE_RENAMES = ImmutableMap.of(
            "minecraft:sugar", "sugar_from_sugar_cane"
    );

    private V2100() {}

    private static void registerMob(final String id) {
        MCTypeRegistry.ENTITY.addWalker(VERSION, id, new DataWalkerItemLists("ArmorItems", "HandItems"));
    }

    public static void register() {
        MCTypeRegistry.RECIPE.addConverter(new DataConverter<>(VERSION) {
            @Override
            public Object convert(final Object data, final long sourceVersion, final long toVersion) {
                if (data instanceof String) {
                    return RECIPE_RENAMES.get((String)data);
                }
                return null;
            }
        });

        registerMob("minecraft:bee");
        registerMob("minecraft:bee_stinger");
        MCTypeRegistry.TILE_ENTITY.addWalker(VERSION, "minecraft:beehive", (data, fromVersion, toVersion) -> {
            final ListType bees = data.getList("Bees", ObjectType.MAP);
            if (bees != null) {
                for (int i = 0, len = bees.size(); i < len; ++i) {
                    WalkerUtils.convert(MCTypeRegistry.ENTITY, bees.getMap(i), "EntityData", fromVersion, toVersion);
                }
            }
        });
    }
}
