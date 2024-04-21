package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.recipe.ConverterAbstractRecipeRename;

import java.util.Map;

public final class V1502 {

    private static final int VERSION = MCVersions.V1_13_PRE2;

    private static final Map<String, String> RECIPES_UPDATES = Map.ofEntries(
            Map.entry("minecraft:acacia_wooden_slab", "minecraft:acacia_slab"),
            Map.entry("minecraft:birch_wooden_slab", "minecraft:birch_slab"),
            Map.entry("minecraft:black_stained_hardened_clay", "minecraft:black_terracotta"),
            Map.entry("minecraft:blue_stained_hardened_clay", "minecraft:blue_terracotta"),
            Map.entry("minecraft:boat", "minecraft:oak_boat"),
            Map.entry("minecraft:bone_meal_from_block", "minecraft:bone_meal_from_bone_block"),
            Map.entry("minecraft:bone_meal_from_bone", "minecraft:bone_meal"),
            Map.entry("minecraft:brick_block", "minecraft:bricks"),
            Map.entry("minecraft:brown_stained_hardened_clay", "minecraft:brown_terracotta"),
            Map.entry("minecraft:chiseled_stonebrick", "minecraft:chiseled_stone_bricks"),
            Map.entry("minecraft:cyan_stained_hardened_clay", "minecraft:cyan_terracotta"),
            Map.entry("minecraft:dark_oak_wooden_slab", "minecraft:dark_oak_slab"),
            Map.entry("minecraft:end_bricks", "minecraft:end_stone_bricks"),
            Map.entry("minecraft:fence_gate", "minecraft:oak_fence_gate"),
            Map.entry("minecraft:fence", "minecraft:oak_fence"),
            Map.entry("minecraft:golden_rail", "minecraft:powered_rail"),
            Map.entry("minecraft:gold_ingot_from_block", "minecraft:gold_ingot_from_gold_block"),
            Map.entry("minecraft:gray_stained_hardened_clay", "minecraft:gray_terracotta"),
            Map.entry("minecraft:green_stained_hardened_clay", "minecraft:green_terracotta"),
            Map.entry("minecraft:iron_ingot_from_block", "minecraft:iron_ingot_from_iron_block"),
            Map.entry("minecraft:jungle_wooden_slab", "minecraft:jungle_slab"),
            Map.entry("minecraft:light_blue_stained_hardened_clay", "minecraft:light_blue_terracotta"),
            Map.entry("minecraft:light_gray_stained_hardened_clay", "minecraft:light_gray_terracotta"),
            Map.entry("minecraft:lime_stained_hardened_clay", "minecraft:lime_terracotta"),
            Map.entry("minecraft:lit_pumpkin", "minecraft:jack_o_lantern"),
            Map.entry("minecraft:magenta_stained_hardened_clay", "minecraft:magenta_terracotta"),
            Map.entry("minecraft:magma", "minecraft:magma_block"),
            Map.entry("minecraft:melon_block", "minecraft:melon"),
            Map.entry("minecraft:mossy_stonebrick", "minecraft:mossy_stone_bricks"),
            Map.entry("minecraft:noteblock", "minecraft:note_block"),
            Map.entry("minecraft:oak_wooden_slab", "minecraft:oak_slab"),
            Map.entry("minecraft:orange_stained_hardened_clay", "minecraft:orange_terracotta"),
            Map.entry("minecraft:pillar_quartz_block", "minecraft:quartz_pillar"),
            Map.entry("minecraft:pink_stained_hardened_clay", "minecraft:pink_terracotta"),
            Map.entry("minecraft:purple_shulker_box", "minecraft:shulker_box"),
            Map.entry("minecraft:purple_stained_hardened_clay", "minecraft:purple_terracotta"),
            Map.entry("minecraft:red_nether_brick", "minecraft:red_nether_bricks"),
            Map.entry("minecraft:red_stained_hardened_clay", "minecraft:red_terracotta"),
            Map.entry("minecraft:slime", "minecraft:slime_block"),
            Map.entry("minecraft:smooth_red_sandstone", "minecraft:cut_red_sandstone"),
            Map.entry("minecraft:smooth_sandstone", "minecraft:cut_sandstone"),
            Map.entry("minecraft:snow_layer", "minecraft:snow"),
            Map.entry("minecraft:snow", "minecraft:snow_block"),
            Map.entry("minecraft:speckled_melon", "minecraft:glistering_melon_slice"),
            Map.entry("minecraft:spruce_wooden_slab", "minecraft:spruce_slab"),
            Map.entry("minecraft:stonebrick", "minecraft:stone_bricks"),
            Map.entry("minecraft:stone_stairs", "minecraft:cobblestone_stairs"),
            Map.entry("minecraft:string_to_wool", "minecraft:white_wool_from_string"),
            Map.entry("minecraft:trapdoor", "minecraft:oak_trapdoor"),
            Map.entry("minecraft:white_stained_hardened_clay", "minecraft:white_terracotta"),
            Map.entry("minecraft:wooden_button", "minecraft:oak_button"),
            Map.entry("minecraft:wooden_door", "minecraft:oak_door"),
            Map.entry("minecraft:wooden_pressure_plate", "minecraft:oak_pressure_plate"),
            Map.entry("minecraft:yellow_stained_hardened_clay", "minecraft:yellow_terracotta")
    );

    public static void register() {
        ConverterAbstractRecipeRename.register(VERSION, RECIPES_UPDATES::get);
    }

    private V1502() {}
}
