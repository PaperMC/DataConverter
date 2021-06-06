package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

public final class V1911 {

    protected static final int VERSION = MCVersions.V18W46A + 1;

    private static final Map<String, String> CHUNK_STATUS_REMAP = ImmutableMap.<String, String>builder()
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
            .build();


    private V1911() {}

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

}
