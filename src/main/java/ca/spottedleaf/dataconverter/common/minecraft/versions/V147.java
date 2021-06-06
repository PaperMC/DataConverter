package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;

public final class V147 {

    protected static final int VERSION = MCVersions.V15W46A + 1;

    public static void register() {
        MCTypeRegistry.ENTITY.addConverterForId("ArmorStand", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                if (data.getBoolean("Silent") && !data.getBoolean("Marker")) {
                    data.remove("Silent");
                }

                return null;
            }
        });
    }

    private V147() {}

}
