package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.types.ListType;
import ca.spottedleaf.dataconverter.types.MapType;
import ca.spottedleaf.dataconverter.types.ObjectType;
import it.unimi.dsi.fastutil.Pair;

import java.util.Map;

public final class V1492 {

    private static final Map<String, Pair<String, Map<String, String>>> RENAMES = Map.ofEntries(
            Map.entry("EndCity", Pair.of(
                    "ECP",
                    Map.of(
                            "second_floor", "second_floor_1",
                            "third_floor", "third_floor_1",
                            "third_floor_c", "third_floor_2"
                    ))
            ),
            Map.entry("Mansion", Pair.of(
                    "WMP",
                    Map.of(
                            "carpet_south", "carpet_south_1",
                            "carpet_west", "carpet_west_1",
                            "indoors_door", "indoors_door_1",
                            "indoors_wall", "indoors_wall_1"
                    ))
            ),
            Map.entry("Igloo", Pair.of(
                    "Iglu",
                    Map.of(
                            "minecraft:igloo/igloo_bottom", "minecraft:igloo/bottom",
                            "minecraft:igloo/igloo_middle", "minecraft:igloo/middle",
                            "minecraft:igloo/igloo_top", "minecraft:igloo/top"
                    ))
            ),
            Map.entry("Ocean_Ruin", Pair.of(
                    "ORP",
                    Map.ofEntries(
                            Map.entry("minecraft:ruin/big_ruin1_brick", "minecraft:underwater_ruin/big_brick_1"),
                            Map.entry("minecraft:ruin/big_ruin2_brick", "minecraft:underwater_ruin/big_brick_2"),
                            Map.entry("minecraft:ruin/big_ruin3_brick", "minecraft:underwater_ruin/big_brick_3"),
                            Map.entry("minecraft:ruin/big_ruin8_brick", "minecraft:underwater_ruin/big_brick_8"),
                            Map.entry("minecraft:ruin/big_ruin1_cracked", "minecraft:underwater_ruin/big_cracked_1"),
                            Map.entry("minecraft:ruin/big_ruin2_cracked", "minecraft:underwater_ruin/big_cracked_2"),
                            Map.entry("minecraft:ruin/big_ruin3_cracked", "minecraft:underwater_ruin/big_cracked_3"),
                            Map.entry("minecraft:ruin/big_ruin8_cracked", "minecraft:underwater_ruin/big_cracked_8"),
                            Map.entry("minecraft:ruin/big_ruin1_mossy", "minecraft:underwater_ruin/big_mossy_1"),
                            Map.entry("minecraft:ruin/big_ruin2_mossy", "minecraft:underwater_ruin/big_mossy_2"),
                            Map.entry("minecraft:ruin/big_ruin3_mossy", "minecraft:underwater_ruin/big_mossy_3"),
                            Map.entry("minecraft:ruin/big_ruin8_mossy", "minecraft:underwater_ruin/big_mossy_8"),
                            Map.entry("minecraft:ruin/big_ruin_warm4", "minecraft:underwater_ruin/big_warm_4"),
                            Map.entry("minecraft:ruin/big_ruin_warm5", "minecraft:underwater_ruin/big_warm_5"),
                            Map.entry("minecraft:ruin/big_ruin_warm6", "minecraft:underwater_ruin/big_warm_6"),
                            Map.entry("minecraft:ruin/big_ruin_warm7", "minecraft:underwater_ruin/big_warm_7"),
                            Map.entry("minecraft:ruin/ruin1_brick", "minecraft:underwater_ruin/brick_1"),
                            Map.entry("minecraft:ruin/ruin2_brick", "minecraft:underwater_ruin/brick_2"),
                            Map.entry("minecraft:ruin/ruin3_brick", "minecraft:underwater_ruin/brick_3"),
                            Map.entry("minecraft:ruin/ruin4_brick", "minecraft:underwater_ruin/brick_4"),
                            Map.entry("minecraft:ruin/ruin5_brick", "minecraft:underwater_ruin/brick_5"),
                            Map.entry("minecraft:ruin/ruin6_brick", "minecraft:underwater_ruin/brick_6"),
                            Map.entry("minecraft:ruin/ruin7_brick", "minecraft:underwater_ruin/brick_7"),
                            Map.entry("minecraft:ruin/ruin8_brick", "minecraft:underwater_ruin/brick_8"),
                            Map.entry("minecraft:ruin/ruin1_cracked", "minecraft:underwater_ruin/cracked_1"),
                            Map.entry("minecraft:ruin/ruin2_cracked", "minecraft:underwater_ruin/cracked_2"),
                            Map.entry("minecraft:ruin/ruin3_cracked", "minecraft:underwater_ruin/cracked_3"),
                            Map.entry("minecraft:ruin/ruin4_cracked", "minecraft:underwater_ruin/cracked_4"),
                            Map.entry("minecraft:ruin/ruin5_cracked", "minecraft:underwater_ruin/cracked_5"),
                            Map.entry("minecraft:ruin/ruin6_cracked", "minecraft:underwater_ruin/cracked_6"),
                            Map.entry("minecraft:ruin/ruin7_cracked", "minecraft:underwater_ruin/cracked_7"),
                            Map.entry("minecraft:ruin/ruin8_cracked", "minecraft:underwater_ruin/cracked_8"),
                            Map.entry("minecraft:ruin/ruin1_mossy", "minecraft:underwater_ruin/mossy_1"),
                            Map.entry("minecraft:ruin/ruin2_mossy", "minecraft:underwater_ruin/mossy_2"),
                            Map.entry("minecraft:ruin/ruin3_mossy", "minecraft:underwater_ruin/mossy_3"),
                            Map.entry("minecraft:ruin/ruin4_mossy", "minecraft:underwater_ruin/mossy_4"),
                            Map.entry("minecraft:ruin/ruin5_mossy", "minecraft:underwater_ruin/mossy_5"),
                            Map.entry("minecraft:ruin/ruin6_mossy", "minecraft:underwater_ruin/mossy_6"),
                            Map.entry("minecraft:ruin/ruin7_mossy", "minecraft:underwater_ruin/mossy_7"),
                            Map.entry("minecraft:ruin/ruin8_mossy", "minecraft:underwater_ruin/mossy_8"),
                            Map.entry("minecraft:ruin/ruin_warm1", "minecraft:underwater_ruin/warm_1"),
                            Map.entry("minecraft:ruin/ruin_warm2", "minecraft:underwater_ruin/warm_2"),
                            Map.entry("minecraft:ruin/ruin_warm3", "minecraft:underwater_ruin/warm_3"),
                            Map.entry("minecraft:ruin/ruin_warm4", "minecraft:underwater_ruin/warm_4"),
                            Map.entry("minecraft:ruin/ruin_warm5", "minecraft:underwater_ruin/warm_5"),
                            Map.entry("minecraft:ruin/ruin_warm6", "minecraft:underwater_ruin/warm_6"),
                            Map.entry("minecraft:ruin/ruin_warm7", "minecraft:underwater_ruin/warm_7"),
                            Map.entry("minecraft:ruin/ruin_warm8", "minecraft:underwater_ruin/warm_8"),
                            Map.entry("minecraft:ruin/big_brick_1", "minecraft:underwater_ruin/big_brick_1"),
                            Map.entry("minecraft:ruin/big_brick_2", "minecraft:underwater_ruin/big_brick_2"),
                            Map.entry("minecraft:ruin/big_brick_3", "minecraft:underwater_ruin/big_brick_3"),
                            Map.entry("minecraft:ruin/big_brick_8", "minecraft:underwater_ruin/big_brick_8"),
                            Map.entry("minecraft:ruin/big_mossy_1", "minecraft:underwater_ruin/big_mossy_1"),
                            Map.entry("minecraft:ruin/big_mossy_2", "minecraft:underwater_ruin/big_mossy_2"),
                            Map.entry("minecraft:ruin/big_mossy_3", "minecraft:underwater_ruin/big_mossy_3"),
                            Map.entry("minecraft:ruin/big_mossy_8", "minecraft:underwater_ruin/big_mossy_8"),
                            Map.entry("minecraft:ruin/big_cracked_1", "minecraft:underwater_ruin/big_cracked_1"),
                            Map.entry("minecraft:ruin/big_cracked_2", "minecraft:underwater_ruin/big_cracked_2"),
                            Map.entry("minecraft:ruin/big_cracked_3", "minecraft:underwater_ruin/big_cracked_3"),
                            Map.entry("minecraft:ruin/big_cracked_8", "minecraft:underwater_ruin/big_cracked_8"),
                            Map.entry("minecraft:ruin/big_warm_4", "minecraft:underwater_ruin/big_warm_4"),
                            Map.entry("minecraft:ruin/big_warm_5", "minecraft:underwater_ruin/big_warm_5"),
                            Map.entry("minecraft:ruin/big_warm_6", "minecraft:underwater_ruin/big_warm_6"),
                            Map.entry("minecraft:ruin/big_warm_7", "minecraft:underwater_ruin/big_warm_7")
                    ))
            ));

    private static final int VERSION = MCVersions.V18W20B + 1;

    public static void register() {
        MCTypeRegistry.STRUCTURE_FEATURE.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final ListType children = data.getList("Children", ObjectType.MAP);
                if (children == null) {
                    return null;
                }

                final String id = data.getString("id");

                final Pair<String, Map<String, String>> renames = RENAMES.get(id);
                if (renames == null) {
                    return null;
                }

                for (int i = 0, len = children.size(); i < len; ++i) {
                    final MapType<String> child = children.getMap(i);

                    if (renames.first().equals(child.getString("id"))) {
                        final String template = child.getString("Template", "");
                        child.setString("Template", renames.second().getOrDefault(template, template));
                    }
                }

                return null;
            }
        });
    }

    private V1492() {
    }
}
