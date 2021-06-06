package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;
import ca.spottedleaf.dataconverter.common.types.Types;

public final class V1946 {

    protected static final int VERSION = MCVersions.V19W14B + 1;

    private V1946() {}

    public static void register() {
        MCTypeRegistry.POI_CHUNK.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final MapType<String> sections = Types.NBT.createEmptyMap();
                data.setMap("Sections", sections);

                for (int y = 0; y < 16; ++y) {
                    final String key = Integer.toString(y);
                    final MapType<String> records = data.getMap(key);

                    if (records == null) {
                        continue;
                    }

                    data.remove(key);

                    final MapType<String> section = Types.NBT.createEmptyMap();
                    section.setMap("Records", records);
                    sections.setMap(key, section);
                }

                return null;
            }
        });
    }

}
