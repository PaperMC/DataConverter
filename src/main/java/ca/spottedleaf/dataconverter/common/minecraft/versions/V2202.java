package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;

public final class V2202 {

    protected static final int VERSION = MCVersions.V19W35A + 1;

    private V2202() {}

    public static void register() {
        MCTypeRegistry.CHUNK.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final MapType<String> level = data.getMap("Level");
                if (level == null) {
                    return null;
                }

                final int[] oldBiomes = level.getInts("Biomes");

                if (oldBiomes == null) {
                    return null;
                }

                final int[] newBiomes = new int[1024];
                level.setInts("Biomes", newBiomes);

                int n;
                for(n = 0; n < 4; ++n) {
                    for(int j = 0; j < 4; ++j) {
                        int k = (j << 2) + 2;
                        int l = (n << 2) + 2;
                        int m = l << 4 | k;
                        newBiomes[n << 2 | j] = m < oldBiomes.length ? oldBiomes[m] : -1;
                    }
                }

                for(n = 1; n < 64; ++n) {
                    System.arraycopy(newBiomes, 0, newBiomes, n * 16, 16);
                }

                return null;
            }
        });
    }
}
