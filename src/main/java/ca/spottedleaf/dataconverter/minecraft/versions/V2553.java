package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.helpers.ConverterAbstractStringValueTypeRename;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;

import java.util.Map;

public final class V2553 {

    private static final int VERSION = MCVersions.V20W20B + 16;

    public static final Map<String, String> BIOME_RENAMES = Map.ofEntries(
            Map.entry("minecraft:extreme_hills", "minecraft:mountains"),
            Map.entry("minecraft:swampland", "minecraft:swamp"),
            Map.entry("minecraft:hell", "minecraft:nether_wastes"),
            Map.entry("minecraft:sky", "minecraft:the_end"),
            Map.entry("minecraft:ice_flats", "minecraft:snowy_tundra"),
            Map.entry("minecraft:ice_mountains", "minecraft:snowy_mountains"),
            Map.entry("minecraft:mushroom_island", "minecraft:mushroom_fields"),
            Map.entry("minecraft:mushroom_island_shore", "minecraft:mushroom_field_shore"),
            Map.entry("minecraft:beaches", "minecraft:beach"),
            Map.entry("minecraft:forest_hills", "minecraft:wooded_hills"),
            Map.entry("minecraft:smaller_extreme_hills", "minecraft:mountain_edge"),
            Map.entry("minecraft:stone_beach", "minecraft:stone_shore"),
            Map.entry("minecraft:cold_beach", "minecraft:snowy_beach"),
            Map.entry("minecraft:roofed_forest", "minecraft:dark_forest"),
            Map.entry("minecraft:taiga_cold", "minecraft:snowy_taiga"),
            Map.entry("minecraft:taiga_cold_hills", "minecraft:snowy_taiga_hills"),
            Map.entry("minecraft:redwood_taiga", "minecraft:giant_tree_taiga"),
            Map.entry("minecraft:redwood_taiga_hills", "minecraft:giant_tree_taiga_hills"),
            Map.entry("minecraft:extreme_hills_with_trees", "minecraft:wooded_mountains"),
            Map.entry("minecraft:savanna_rock", "minecraft:savanna_plateau"),
            Map.entry("minecraft:mesa", "minecraft:badlands"),
            Map.entry("minecraft:mesa_rock", "minecraft:wooded_badlands_plateau"),
            Map.entry("minecraft:mesa_clear_rock", "minecraft:badlands_plateau"),
            Map.entry("minecraft:sky_island_low", "minecraft:small_end_islands"),
            Map.entry("minecraft:sky_island_medium", "minecraft:end_midlands"),
            Map.entry("minecraft:sky_island_high", "minecraft:end_highlands"),
            Map.entry("minecraft:sky_island_barren", "minecraft:end_barrens"),
            Map.entry("minecraft:void", "minecraft:the_void"),
            Map.entry("minecraft:mutated_plains", "minecraft:sunflower_plains"),
            Map.entry("minecraft:mutated_desert", "minecraft:desert_lakes"),
            Map.entry("minecraft:mutated_extreme_hills", "minecraft:gravelly_mountains"),
            Map.entry("minecraft:mutated_forest", "minecraft:flower_forest"),
            Map.entry("minecraft:mutated_taiga", "minecraft:taiga_mountains"),
            Map.entry("minecraft:mutated_swampland", "minecraft:swamp_hills"),
            Map.entry("minecraft:mutated_ice_flats", "minecraft:ice_spikes"),
            Map.entry("minecraft:mutated_jungle", "minecraft:modified_jungle"),
            Map.entry("minecraft:mutated_jungle_edge", "minecraft:modified_jungle_edge"),
            Map.entry("minecraft:mutated_birch_forest", "minecraft:tall_birch_forest"),
            Map.entry("minecraft:mutated_birch_forest_hills", "minecraft:tall_birch_hills"),
            Map.entry("minecraft:mutated_roofed_forest", "minecraft:dark_forest_hills"),
            Map.entry("minecraft:mutated_taiga_cold", "minecraft:snowy_taiga_mountains"),
            Map.entry("minecraft:mutated_redwood_taiga", "minecraft:giant_spruce_taiga"),
            Map.entry("minecraft:mutated_redwood_taiga_hills", "minecraft:giant_spruce_taiga_hills"),
            Map.entry("minecraft:mutated_extreme_hills_with_trees", "minecraft:modified_gravelly_mountains"),
            Map.entry("minecraft:mutated_savanna", "minecraft:shattered_savanna"),
            Map.entry("minecraft:mutated_savanna_rock", "minecraft:shattered_savanna_plateau"),
            Map.entry("minecraft:mutated_mesa", "minecraft:eroded_badlands"),
            Map.entry("minecraft:mutated_mesa_rock", "minecraft:modified_wooded_badlands_plateau"),
            Map.entry("minecraft:mutated_mesa_clear_rock", "minecraft:modified_badlands_plateau"),
            Map.entry("minecraft:warm_deep_ocean", "minecraft:deep_warm_ocean"),
            Map.entry("minecraft:lukewarm_deep_ocean", "minecraft:deep_lukewarm_ocean"),
            Map.entry("minecraft:cold_deep_ocean", "minecraft:deep_cold_ocean"),
            Map.entry("minecraft:frozen_deep_ocean", "minecraft:deep_frozen_ocean")
    );

    public static void register() {
        ConverterAbstractStringValueTypeRename.register(VERSION, MCTypeRegistry.BIOME, BIOME_RENAMES::get);
    }

    private V2553() {}
}
