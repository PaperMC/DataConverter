package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;

public final class V1917 {

    protected static final int VERSION = MCVersions.V18W49A + 1;

    private V1917() {}

    public static void register() {
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:cat", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                if (data.getInt("CatType") == 9) {
                    data.setInt("CatType", 10);
                }
                return null;
            }
        });
    }

}
