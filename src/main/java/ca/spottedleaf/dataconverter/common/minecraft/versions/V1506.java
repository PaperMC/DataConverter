package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class V1506 {

    protected static final int VERSION = MCVersions.V1_13_PRE4 + 2;

    static final Map<String, String> MAP = new HashMap<>();
    static {
        MAP.put("0", "minecraft:ocean");
        MAP.put("1", "minecraft:plains");
        MAP.put("2", "minecraft:desert");
        MAP.put("3", "minecraft:mountains");
        MAP.put("4", "minecraft:forest");
        MAP.put("5", "minecraft:taiga");
        MAP.put("6", "minecraft:swamp");
        MAP.put("7", "minecraft:river");
        MAP.put("8", "minecraft:nether");
        MAP.put("9", "minecraft:the_end");
        MAP.put("10", "minecraft:frozen_ocean");
        MAP.put("11", "minecraft:frozen_river");
        MAP.put("12", "minecraft:snowy_tundra");
        MAP.put("13", "minecraft:snowy_mountains");
        MAP.put("14", "minecraft:mushroom_fields");
        MAP.put("15", "minecraft:mushroom_field_shore");
        MAP.put("16", "minecraft:beach");
        MAP.put("17", "minecraft:desert_hills");
        MAP.put("18", "minecraft:wooded_hills");
        MAP.put("19", "minecraft:taiga_hills");
        MAP.put("20", "minecraft:mountain_edge");
        MAP.put("21", "minecraft:jungle");
        MAP.put("22", "minecraft:jungle_hills");
        MAP.put("23", "minecraft:jungle_edge");
        MAP.put("24", "minecraft:deep_ocean");
        MAP.put("25", "minecraft:stone_shore");
        MAP.put("26", "minecraft:snowy_beach");
        MAP.put("27", "minecraft:birch_forest");
        MAP.put("28", "minecraft:birch_forest_hills");
        MAP.put("29", "minecraft:dark_forest");
        MAP.put("30", "minecraft:snowy_taiga");
        MAP.put("31", "minecraft:snowy_taiga_hills");
        MAP.put("32", "minecraft:giant_tree_taiga");
        MAP.put("33", "minecraft:giant_tree_taiga_hills");
        MAP.put("34", "minecraft:wooded_mountains");
        MAP.put("35", "minecraft:savanna");
        MAP.put("36", "minecraft:savanna_plateau");
        MAP.put("37", "minecraft:badlands");
        MAP.put("38", "minecraft:wooded_badlands_plateau");
        MAP.put("39", "minecraft:badlands_plateau");
        MAP.put("40", "minecraft:small_end_islands");
        MAP.put("41", "minecraft:end_midlands");
        MAP.put("42", "minecraft:end_highlands");
        MAP.put("43", "minecraft:end_barrens");
        MAP.put("44", "minecraft:warm_ocean");
        MAP.put("45", "minecraft:lukewarm_ocean");
        MAP.put("46", "minecraft:cold_ocean");
        MAP.put("47", "minecraft:deep_warm_ocean");
        MAP.put("48", "minecraft:deep_lukewarm_ocean");
        MAP.put("49", "minecraft:deep_cold_ocean");
        MAP.put("50", "minecraft:deep_frozen_ocean");
        MAP.put("127", "minecraft:the_void");
        MAP.put("129", "minecraft:sunflower_plains");
        MAP.put("130", "minecraft:desert_lakes");
        MAP.put("131", "minecraft:gravelly_mountains");
        MAP.put("132", "minecraft:flower_forest");
        MAP.put("133", "minecraft:taiga_mountains");
        MAP.put("134", "minecraft:swamp_hills");
        MAP.put("140", "minecraft:ice_spikes");
        MAP.put("149", "minecraft:modified_jungle");
        MAP.put("151", "minecraft:modified_jungle_edge");
        MAP.put("155", "minecraft:tall_birch_forest");
        MAP.put("156", "minecraft:tall_birch_hills");
        MAP.put("157", "minecraft:dark_forest_hills");
        MAP.put("158", "minecraft:snowy_taiga_mountains");
        MAP.put("160", "minecraft:giant_spruce_taiga");
        MAP.put("161", "minecraft:giant_spruce_taiga_hills");
        MAP.put("162", "minecraft:modified_gravelly_mountains");
        MAP.put("163", "minecraft:shattered_savanna");
        MAP.put("164", "minecraft:shattered_savanna_plateau");
        MAP.put("165", "minecraft:eroded_badlands");
        MAP.put("166", "minecraft:modified_wooded_badlands_plateau");
        MAP.put("167", "minecraft:modified_badlands_plateau");
    }

    private V1506() {}

    // I only keep non-chunk converters around for completion. so im not commenting or mapping this one.
    public static void register() {
        MCTypeRegistry.LEVEL.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                // TODO ahh shit what the fuck to do about generatorOptions
                return null;
            }
        });
    }

    private static Pair<Integer, String> getLayerInfoFromString(String string) {
        String[] strings = string.split("\\*", 2);
        int j;
        if (strings.length == 2) {
            try {
                j = Integer.parseInt(strings[0]);
            } catch (NumberFormatException var4) {
                return null;
            }
        } else {
            j = 1;
        }

        String string2 = strings[strings.length - 1];
        return Pair.of(j, string2);
    }

    private static List<Pair<Integer, String>> getLayersInfoFromString(String string) {
        List<Pair<Integer, String>> list = Lists.newArrayList();
        String[] strings = string.split(",");
        String[] var3 = strings;
        int var4 = strings.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String string2 = var3[var5];
            Pair<Integer, String> pair = getLayerInfoFromString(string2);
            if (pair == null) {
                return Collections.emptyList();
            }

            list.add(pair);
        }

        return list;
    }
}
