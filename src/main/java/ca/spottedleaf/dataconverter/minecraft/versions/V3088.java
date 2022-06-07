package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.types.MapType;
import ca.spottedleaf.dataconverter.types.Types;
import ca.spottedleaf.dataconverter.util.NamespaceUtil;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class V3088 {

    // this class originally targeted 3079 but was changed to target a later version without changing the converter, zero clue why
    protected static final int VERSION = MCVersions.V22W14A;

    private static final Set<String> STATUSES_TO_SKIP_BLENDING = new HashSet<>(
            Arrays.asList(
                    "minecraft:empty",
                    "minecraft:structure_starts",
                    "minecraft:structure_references",
                    "minecraft:biomes"
            )
    );

    public static void register() {
        MCTypeRegistry.CHUNK.addStructureConverter(new DataConverter<>(VERSION) {

            private static MapType<String> createBlendingData(final int height, final int minY) {
                final MapType<String> ret = Types.NBT.createEmptyMap();

                ret.setInt("min_section", minY >> 4);
                ret.setInt("max_section", (minY + height) >> 4);

                return ret;
            }

            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                data.remove("blending_data");

                final String status = NamespaceUtil.correctNamespace(data.getString("Status"));
                if (status == null) {
                    return null;
                }

                final MapType<String> belowZeroRetrogen = data.getMap("below_zero_retrogen");

                if (!STATUSES_TO_SKIP_BLENDING.contains(status)) {
                    data.setMap("blending_data", createBlendingData(384, -64));
                } else if (belowZeroRetrogen != null) {
                    final String realStatus = NamespaceUtil.correctNamespace(belowZeroRetrogen.getString("target_status", "empty"));
                    if (!STATUSES_TO_SKIP_BLENDING.contains(realStatus)) {
                        data.setMap("blending_data", createBlendingData(256, 0));
                    }
                }

                return null;
            }
        });
    }
}
