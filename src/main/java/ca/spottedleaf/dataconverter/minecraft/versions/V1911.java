package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.types.MapType;

import java.util.Map;

public final class V1911 {

    private static final int VERSION = MCVersions.V18W46A + 1;

    private static final Map<String, String> CHUNK_STATUS_REMAP = Map.ofEntries(
            Map.entry("structure_references", "empty"),
            Map.entry("biomes", "empty"),
            Map.entry("base", "surface"),
            Map.entry("carved", "carvers"),
            Map.entry("liquid_carved", "liquid_carvers"),
            Map.entry("decorated", "features"),
            Map.entry("lighted", "light"),
            Map.entry("mobs_spawned", "spawn"),
            Map.entry("finalized", "heightmaps"),
            Map.entry("fullchunk", "full")
    );

    public static void register() {
        MCTypeRegistry.CHUNK.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final MapType<String> level = data.getMap("Level");

                if (level == null) {
                    return null;
                }

                final String status = level.getString("Status", "empty");
                level.setString("Status", CHUNK_STATUS_REMAP.getOrDefault(status, "empty"));

                return null;
            }
        });
    }

    private V1911() {}
}
