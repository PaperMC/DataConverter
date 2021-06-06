package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;

public final class V143 {

    protected static final int VERSION = MCVersions.V15W44B;

    public static void register() {
        MCTypeRegistry.ENTITY.addConverterForId("TippedArrow", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                data.setString("id", "Arrow");
                return null;
            }
        });
    }

    private V143() {

    }

}
