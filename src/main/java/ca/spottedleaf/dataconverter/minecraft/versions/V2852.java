package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.types.MapType;

public final class V2852 {

    private static final int VERSION = MCVersions.V1_18_PRE5 + 1;

    public static void register() {
        MCTypeRegistry.WORLD_GEN_SETTINGS.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final MapType<String> dimensions = data.getMap("dimensions");

                for (final String dimensionKey : dimensions.keys()) {
                    final MapType<String> dimension = dimensions.getMap(dimensionKey);
                    if (!dimension.hasKey("type")) {
                        throw new IllegalStateException("Unable load old custom worlds.");
                    }
                }

                return null;
            }
        });
    }

    private V2852() {}
}
