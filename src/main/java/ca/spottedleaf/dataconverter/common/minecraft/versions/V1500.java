package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;

public final class V1500 {

    protected static final int VERSION = MCVersions.V18W22C + 1;

    private V1500() {}

    public static void register() {
        MCTypeRegistry.TILE_ENTITY.addConverterForId("DUMMY", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                data.setBoolean("keepPacked", true);
                return null;
            }
        });
    }

}
