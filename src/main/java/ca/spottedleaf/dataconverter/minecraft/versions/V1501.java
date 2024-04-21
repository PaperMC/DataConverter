package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.advancements.ConverterAbstractAdvancementsRename;

import java.util.Map;

public final class V1501 {

    private static final int VERSION = MCVersions.V1_13_PRE1;

    private static final Map<String, String> RENAMES = Map.ofEntries(
            Map.entry("minecraft:recipes/brewing/speckled_melon", "minecraft:recipes/brewing/glistering_melon_slice"),
            Map.entry("minecraft:recipes/building_blocks/black_stained_hardened_clay", "minecraft:recipes/building_blocks/black_terracotta"),
            Map.entry("minecraft:recipes/building_blocks/blue_stained_hardened_clay", "minecraft:recipes/building_blocks/blue_terracotta"),
            Map.entry("minecraft:recipes/building_blocks/brown_stained_hardened_clay", "minecraft:recipes/building_blocks/brown_terracotta"),
            Map.entry("minecraft:recipes/building_blocks/cyan_stained_hardened_clay", "minecraft:recipes/building_blocks/cyan_terracotta"),
            Map.entry("minecraft:recipes/building_blocks/gray_stained_hardened_clay", "minecraft:recipes/building_blocks/gray_terracotta"),
            Map.entry("minecraft:recipes/building_blocks/green_stained_hardened_clay", "minecraft:recipes/building_blocks/green_terracotta"),
            Map.entry("minecraft:recipes/building_blocks/light_blue_stained_hardened_clay", "minecraft:recipes/building_blocks/light_blue_terracotta"),
            Map.entry("minecraft:recipes/building_blocks/light_gray_stained_hardened_clay", "minecraft:recipes/building_blocks/light_gray_terracotta"),
            Map.entry("minecraft:recipes/building_blocks/lime_stained_hardened_clay", "minecraft:recipes/building_blocks/lime_terracotta"),
            Map.entry("minecraft:recipes/building_blocks/magenta_stained_hardened_clay", "minecraft:recipes/building_blocks/magenta_terracotta"),
            Map.entry("minecraft:recipes/building_blocks/orange_stained_hardened_clay", "minecraft:recipes/building_blocks/orange_terracotta"),
            Map.entry("minecraft:recipes/building_blocks/pink_stained_hardened_clay", "minecraft:recipes/building_blocks/pink_terracotta"),
            Map.entry("minecraft:recipes/building_blocks/purple_stained_hardened_clay", "minecraft:recipes/building_blocks/purple_terracotta"),
            Map.entry("minecraft:recipes/building_blocks/red_stained_hardened_clay", "minecraft:recipes/building_blocks/red_terracotta"),
            Map.entry("minecraft:recipes/building_blocks/white_stained_hardened_clay", "minecraft:recipes/building_blocks/white_terracotta"),
            Map.entry("minecraft:recipes/building_blocks/yellow_stained_hardened_clay", "minecraft:recipes/building_blocks/yellow_terracotta"),
            Map.entry("minecraft:recipes/building_blocks/acacia_wooden_slab", "minecraft:recipes/building_blocks/acacia_slab"),
            Map.entry("minecraft:recipes/building_blocks/birch_wooden_slab", "minecraft:recipes/building_blocks/birch_slab"),
            Map.entry("minecraft:recipes/building_blocks/dark_oak_wooden_slab", "minecraft:recipes/building_blocks/dark_oak_slab"),
            Map.entry("minecraft:recipes/building_blocks/jungle_wooden_slab", "minecraft:recipes/building_blocks/jungle_slab"),
            Map.entry("minecraft:recipes/building_blocks/oak_wooden_slab", "minecraft:recipes/building_blocks/oak_slab"),
            Map.entry("minecraft:recipes/building_blocks/spruce_wooden_slab", "minecraft:recipes/building_blocks/spruce_slab"),
            Map.entry("minecraft:recipes/building_blocks/brick_block", "minecraft:recipes/building_blocks/bricks"),
            Map.entry("minecraft:recipes/building_blocks/chiseled_stonebrick", "minecraft:recipes/building_blocks/chiseled_stone_bricks"),
            Map.entry("minecraft:recipes/building_blocks/end_bricks", "minecraft:recipes/building_blocks/end_stone_bricks"),
            Map.entry("minecraft:recipes/building_blocks/lit_pumpkin", "minecraft:recipes/building_blocks/jack_o_lantern"),
            Map.entry("minecraft:recipes/building_blocks/magma", "minecraft:recipes/building_blocks/magma_block"),
            Map.entry("minecraft:recipes/building_blocks/melon_block", "minecraft:recipes/building_blocks/melon"),
            Map.entry("minecraft:recipes/building_blocks/mossy_stonebrick", "minecraft:recipes/building_blocks/mossy_stone_bricks"),
            Map.entry("minecraft:recipes/building_blocks/nether_brick", "minecraft:recipes/building_blocks/nether_bricks"),
            Map.entry("minecraft:recipes/building_blocks/pillar_quartz_block", "minecraft:recipes/building_blocks/quartz_pillar"),
            Map.entry("minecraft:recipes/building_blocks/red_nether_brick", "minecraft:recipes/building_blocks/red_nether_bricks"),
            Map.entry("minecraft:recipes/building_blocks/snow", "minecraft:recipes/building_blocks/snow_block"),
            Map.entry("minecraft:recipes/building_blocks/smooth_red_sandstone", "minecraft:recipes/building_blocks/cut_red_sandstone"),
            Map.entry("minecraft:recipes/building_blocks/smooth_sandstone", "minecraft:recipes/building_blocks/cut_sandstone"),
            Map.entry("minecraft:recipes/building_blocks/stonebrick", "minecraft:recipes/building_blocks/stone_bricks"),
            Map.entry("minecraft:recipes/building_blocks/stone_stairs", "minecraft:recipes/building_blocks/cobblestone_stairs"),
            Map.entry("minecraft:recipes/building_blocks/string_to_wool", "minecraft:recipes/building_blocks/white_wool_from_string"),
            Map.entry("minecraft:recipes/decorations/fence", "minecraft:recipes/decorations/oak_fence"),
            Map.entry("minecraft:recipes/decorations/purple_shulker_box", "minecraft:recipes/decorations/shulker_box"),
            Map.entry("minecraft:recipes/decorations/slime", "minecraft:recipes/decorations/slime_block"),
            Map.entry("minecraft:recipes/decorations/snow_layer", "minecraft:recipes/decorations/snow"),
            Map.entry("minecraft:recipes/misc/bone_meal_from_block", "minecraft:recipes/misc/bone_meal_from_bone_block"),
            Map.entry("minecraft:recipes/misc/bone_meal_from_bone", "minecraft:recipes/misc/bone_meal"),
            Map.entry("minecraft:recipes/misc/gold_ingot_from_block", "minecraft:recipes/misc/gold_ingot_from_gold_block"),
            Map.entry("minecraft:recipes/misc/iron_ingot_from_block", "minecraft:recipes/misc/iron_ingot_from_iron_block"),
            Map.entry("minecraft:recipes/redstone/fence_gate", "minecraft:recipes/redstone/oak_fence_gate"),
            Map.entry("minecraft:recipes/redstone/noteblock", "minecraft:recipes/redstone/note_block"),
            Map.entry("minecraft:recipes/redstone/trapdoor", "minecraft:recipes/redstone/oak_trapdoor"),
            Map.entry("minecraft:recipes/redstone/wooden_button", "minecraft:recipes/redstone/oak_button"),
            Map.entry("minecraft:recipes/redstone/wooden_door", "minecraft:recipes/redstone/oak_door"),
            Map.entry("minecraft:recipes/redstone/wooden_pressure_plate", "minecraft:recipes/redstone/oak_pressure_plate"),
            Map.entry("minecraft:recipes/transportation/boat", "minecraft:recipes/transportation/oak_boat"),
            Map.entry("minecraft:recipes/transportation/golden_rail", "minecraft:recipes/transportation/powered_rail")
    );

    public static void register() {
        ConverterAbstractAdvancementsRename.register(VERSION, RENAMES::get);
    }

    private V1501() {}
}
