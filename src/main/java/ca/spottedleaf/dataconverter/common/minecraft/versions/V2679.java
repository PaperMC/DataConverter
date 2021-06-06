package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;

public final class V2679 {

    protected static final int VERSION = MCVersions.V1_16_5 + 93;

    public static void register() {
        MCTypeRegistry.BLOCK_STATE.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                if (!"minecraft:cauldron".equals(data.getString("Name"))) {
                    return null;
                }

                final MapType<String> properties = data.getMap("Properties");

                if (properties == null) {
                    return null;
                }

                if (properties.getString("level", "0").equals("0")) {
                    data.remove("Properties");
                    return null;
                } else {
                    data.setString("Name", "minecraft:water_cauldron");
                    return null;
                }
            }
        });
    }
}
