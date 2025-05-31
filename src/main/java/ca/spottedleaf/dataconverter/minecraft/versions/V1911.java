package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.types.MapType;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;

public final class V1911 {

    private static final int VERSION = MCVersions.V18W46A + 1;

    private static final Map<String, String> CHUNK_STATUS_REMAP = new HashMap<>(
            ImmutableMap.<String, String>builder()
                    .put("structure_references", "empty")
                    .put("biomes", "empty")
                    .put("base", "surface")
                    .put("carved", "carvers")
                    .put("liquid_carved", "liquid_carvers")
                    .put("decorated", "features")
                    .put("lighted", "light")
                    .put("mobs_spawned", "spawn")
                    .put("finalized", "heightmaps")
                    .put("fullchunk", "full")
                    .build()
    );

    public static void register() {
        MCTypeRegistry.CHUNK.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType convert(final MapType data, final long sourceVersion, final long toVersion) {
                final MapType level = data.getMap("Level");

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
