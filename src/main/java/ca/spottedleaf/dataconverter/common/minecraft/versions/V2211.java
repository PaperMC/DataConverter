package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;
import ca.spottedleaf.dataconverter.common.types.ObjectType;

public final class V2211 {

    protected static final int VERSION = MCVersions.V19W41A + 1;

    private V2211() {}

    public static void register() {
        MCTypeRegistry.STRUCTURE_FEATURE.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                if (!data.hasKey("references", ObjectType.NUMBER)) {
                    return null;
                }

                final int references = data.getInt("references");
                if (references <= 0) {
                    data.setInt("references", 1);
                }
                return null;
            }
        });
    }

}
