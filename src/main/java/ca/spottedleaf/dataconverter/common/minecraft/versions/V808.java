package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.minecraft.walkers.itemstack.DataWalkerItemLists;
import ca.spottedleaf.dataconverter.common.types.ObjectType;
import ca.spottedleaf.dataconverter.common.types.MapType;

public final class V808 {

    protected static final int VERSION = MCVersions.V16W38A + 1;

    public static void register() {
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:shulker", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                if (!data.hasKey("Color", ObjectType.NUMBER)) {
                    data.setByte("Color", (byte)10);
                }
                return null;
            }
        });

        MCTypeRegistry.TILE_ENTITY.addWalker(VERSION, "minecraft:shulker_box", new DataWalkerItemLists("Items"));
    }

    private V808() {}

}
