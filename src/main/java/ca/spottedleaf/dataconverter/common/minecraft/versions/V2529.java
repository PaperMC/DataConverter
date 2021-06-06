package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;

public final class V2529 {

    protected static final int VERSION = MCVersions.V20W17A;

    private V2529() {}

    public static void register() {
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:strider", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                if (data.getBoolean("NoGravity")) {
                    data.setBoolean("NoGravity", false);
                }
                return null;
            }
        });
    }
}
