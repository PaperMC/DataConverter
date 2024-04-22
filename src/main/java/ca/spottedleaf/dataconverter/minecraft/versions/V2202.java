package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.types.MapType;

public final class V2202 {

    private static final int VERSION = MCVersions.V19W35A + 1;

    public static void register() {
        MCTypeRegistry.CHUNK.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final MapType<String> level = data.getMap("Level");
                if (level == null) {
                    return null;
                }

                final int[] oldBiomes = level.getInts("Biomes");

                if (oldBiomes == null || oldBiomes.length != 256) {
                    return null;
                }

                final int[] newBiomes = new int[1024];
                level.setInts("Biomes", newBiomes);

                for (int i = 0; i < 4; ++i) {
                    for (int j = 0; j < 4; ++j) {
                        int k = (j << 2) + 2;
                        int l = (i << 2) + 2;
                        int m = l << 4 | k;
                        newBiomes[i << 2 | j] = oldBiomes[m];
                    }
                }

                for (int i = 1; i < 64; ++i) {
                    System.arraycopy(newBiomes, 0, newBiomes, i * 16, 16);
                }

                return null;
            }
        });
    }

    private V2202() {}
}
