package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.helpers.ConverterAbstractStringValueTypeRename;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;

import java.util.Map;

public final class V2838 {

    private static final int VERSION = MCVersions.V21W40A;

    public static final Map<String, String> BIOME_UPDATE = Map.ofEntries(
            Map.entry("minecraft:badlands_plateau", "minecraft:badlands"),
            Map.entry("minecraft:bamboo_jungle_hills", "minecraft:bamboo_jungle"),
            Map.entry("minecraft:birch_forest_hills", "minecraft:birch_forest"),
            Map.entry("minecraft:dark_forest_hills", "minecraft:dark_forest"),
            Map.entry("minecraft:desert_hills", "minecraft:desert"),
            Map.entry("minecraft:desert_lakes", "minecraft:desert"),
            Map.entry("minecraft:giant_spruce_taiga_hills", "minecraft:old_growth_spruce_taiga"),
            Map.entry("minecraft:giant_spruce_taiga", "minecraft:old_growth_spruce_taiga"),
            Map.entry("minecraft:giant_tree_taiga_hills", "minecraft:old_growth_pine_taiga"),
            Map.entry("minecraft:giant_tree_taiga", "minecraft:old_growth_pine_taiga"),
            Map.entry("minecraft:gravelly_mountains", "minecraft:windswept_gravelly_hills"),
            Map.entry("minecraft:jungle_edge", "minecraft:sparse_jungle"),
            Map.entry("minecraft:jungle_hills", "minecraft:jungle"),
            Map.entry("minecraft:modified_badlands_plateau", "minecraft:badlands"),
            Map.entry("minecraft:modified_gravelly_mountains", "minecraft:windswept_gravelly_hills"),
            Map.entry("minecraft:modified_jungle_edge", "minecraft:sparse_jungle"),
            Map.entry("minecraft:modified_jungle", "minecraft:jungle"),
            Map.entry("minecraft:modified_wooded_badlands_plateau", "minecraft:wooded_badlands"),
            Map.entry("minecraft:mountain_edge", "minecraft:windswept_hills"),
            Map.entry("minecraft:mountains", "minecraft:windswept_hills"),
            Map.entry("minecraft:mushroom_field_shore", "minecraft:mushroom_fields"),
            Map.entry("minecraft:shattered_savanna", "minecraft:windswept_savanna"),
            Map.entry("minecraft:shattered_savanna_plateau", "minecraft:windswept_savanna"),
            Map.entry("minecraft:snowy_mountains", "minecraft:snowy_plains"),
            Map.entry("minecraft:snowy_taiga_hills", "minecraft:snowy_taiga"),
            Map.entry("minecraft:snowy_taiga_mountains", "minecraft:snowy_taiga"),
            Map.entry("minecraft:snowy_tundra", "minecraft:snowy_plains"),
            Map.entry("minecraft:stone_shore", "minecraft:stony_shore"),
            Map.entry("minecraft:swamp_hills", "minecraft:swamp"),
            Map.entry("minecraft:taiga_hills", "minecraft:taiga"),
            Map.entry("minecraft:taiga_mountains", "minecraft:taiga"),
            Map.entry("minecraft:tall_birch_forest", "minecraft:old_growth_birch_forest"),
            Map.entry("minecraft:tall_birch_hills", "minecraft:old_growth_birch_forest"),
            Map.entry("minecraft:wooded_badlands_plateau", "minecraft:wooded_badlands"),
            Map.entry("minecraft:wooded_hills", "minecraft:forest"),
            Map.entry("minecraft:wooded_mountains", "minecraft:windswept_forest"),
            Map.entry("minecraft:lofty_peaks", "minecraft:jagged_peaks"),
            Map.entry("minecraft:snowcapped_peaks", "minecraft:frozen_peaks")
    );

    public static void register() {
        ConverterAbstractStringValueTypeRename.register(VERSION, MCTypeRegistry.BIOME, BIOME_UPDATE::get);
    }

    private V2838() {}
}
