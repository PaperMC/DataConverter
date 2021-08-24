package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.converters.blockname.ConverterAbstractBlockRename;
import ca.spottedleaf.dataconverter.common.minecraft.converters.itemname.ConverterAbstractItemRename;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;

public final class V1474 {

    protected static final int VERSION = MCVersions.V18W10B;

    public static void register() {
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:shulker", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                if (data.getInt("Color") == 10) {
                    data.setByte("Color", (byte)16);
                }
                return null;
            }
        });
        // data hooks ensure the inputs are namespaced
        ConverterAbstractBlockRename.register(VERSION, (final String old) -> {
            return "minecraft:purple_shulker_box".equals(old) ? "minecraft:shulker_box" : null;
        });
        ConverterAbstractItemRename.register(VERSION, (final String old) -> {
            return "minecraft:purple_shulker_box".equals(old) ? "minecraft:shulker_box" : null;
        });

    }

    private V1474() {}

}
