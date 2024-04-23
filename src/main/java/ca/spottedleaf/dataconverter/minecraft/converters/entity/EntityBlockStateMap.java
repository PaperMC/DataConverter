package ca.spottedleaf.dataconverter.minecraft.converters.entity;

import java.util.Map;

public class EntityBlockStateMap {
    private static final Map<String, Integer> MAP = Map.ofEntries(
            Map.<String, Integer>entry("minecraft:air", 0),
            Map.<String, Integer>entry("minecraft:stone", 1),
            Map.<String, Integer>entry("minecraft:grass", 2),
            Map.<String, Integer>entry("minecraft:dirt", 3),
            Map.<String, Integer>entry("minecraft:cobblestone", 4),
            Map.<String, Integer>entry("minecraft:planks", 5),
            Map.<String, Integer>entry("minecraft:sapling", 6),
            Map.<String, Integer>entry("minecraft:bedrock", 7),
            Map.<String, Integer>entry("minecraft:flowing_water", 8),
            Map.<String, Integer>entry("minecraft:water", 9),
            Map.<String, Integer>entry("minecraft:flowing_lava", 10),
            Map.<String, Integer>entry("minecraft:lava", 11),
            Map.<String, Integer>entry("minecraft:sand", 12),
            Map.<String, Integer>entry("minecraft:gravel", 13),
            Map.<String, Integer>entry("minecraft:gold_ore", 14),
            Map.<String, Integer>entry("minecraft:iron_ore", 15),
            Map.<String, Integer>entry("minecraft:coal_ore", 16),
            Map.<String, Integer>entry("minecraft:log", 17),
            Map.<String, Integer>entry("minecraft:leaves", 18),
            Map.<String, Integer>entry("minecraft:sponge", 19),
            Map.<String, Integer>entry("minecraft:glass", 20),
            Map.<String, Integer>entry("minecraft:lapis_ore", 21),
            Map.<String, Integer>entry("minecraft:lapis_block", 22),
            Map.<String, Integer>entry("minecraft:dispenser", 23),
            Map.<String, Integer>entry("minecraft:sandstone", 24),
            Map.<String, Integer>entry("minecraft:noteblock", 25),
            Map.<String, Integer>entry("minecraft:bed", 26),
            Map.<String, Integer>entry("minecraft:golden_rail", 27),
            Map.<String, Integer>entry("minecraft:detector_rail", 28),
            Map.<String, Integer>entry("minecraft:sticky_piston", 29),
            Map.<String, Integer>entry("minecraft:web", 30),
            Map.<String, Integer>entry("minecraft:tallgrass", 31),
            Map.<String, Integer>entry("minecraft:deadbush", 32),
            Map.<String, Integer>entry("minecraft:piston", 33),
            Map.<String, Integer>entry("minecraft:piston_head", 34),
            Map.<String, Integer>entry("minecraft:wool", 35),
            Map.<String, Integer>entry("minecraft:piston_extension", 36),
            Map.<String, Integer>entry("minecraft:yellow_flower", 37),
            Map.<String, Integer>entry("minecraft:red_flower", 38),
            Map.<String, Integer>entry("minecraft:brown_mushroom", 39),
            Map.<String, Integer>entry("minecraft:red_mushroom", 40),
            Map.<String, Integer>entry("minecraft:gold_block", 41),
            Map.<String, Integer>entry("minecraft:iron_block", 42),
            Map.<String, Integer>entry("minecraft:double_stone_slab", 43),
            Map.<String, Integer>entry("minecraft:stone_slab", 44),
            Map.<String, Integer>entry("minecraft:brick_block", 45),
            Map.<String, Integer>entry("minecraft:tnt", 46),
            Map.<String, Integer>entry("minecraft:bookshelf", 47),
            Map.<String, Integer>entry("minecraft:mossy_cobblestone", 48),
            Map.<String, Integer>entry("minecraft:obsidian", 49),
            Map.<String, Integer>entry("minecraft:torch", 50),
            Map.<String, Integer>entry("minecraft:fire", 51),
            Map.<String, Integer>entry("minecraft:mob_spawner", 52),
            Map.<String, Integer>entry("minecraft:oak_stairs", 53),
            Map.<String, Integer>entry("minecraft:chest", 54),
            Map.<String, Integer>entry("minecraft:redstone_wire", 55),
            Map.<String, Integer>entry("minecraft:diamond_ore", 56),
            Map.<String, Integer>entry("minecraft:diamond_block", 57),
            Map.<String, Integer>entry("minecraft:crafting_table", 58),
            Map.<String, Integer>entry("minecraft:wheat", 59),
            Map.<String, Integer>entry("minecraft:farmland", 60),
            Map.<String, Integer>entry("minecraft:furnace", 61),
            Map.<String, Integer>entry("minecraft:lit_furnace", 62),
            Map.<String, Integer>entry("minecraft:standing_sign", 63),
            Map.<String, Integer>entry("minecraft:wooden_door", 64),
            Map.<String, Integer>entry("minecraft:ladder", 65),
            Map.<String, Integer>entry("minecraft:rail", 66),
            Map.<String, Integer>entry("minecraft:stone_stairs", 67),
            Map.<String, Integer>entry("minecraft:wall_sign", 68),
            Map.<String, Integer>entry("minecraft:lever", 69),
            Map.<String, Integer>entry("minecraft:stone_pressure_plate", 70),
            Map.<String, Integer>entry("minecraft:iron_door", 71),
            Map.<String, Integer>entry("minecraft:wooden_pressure_plate", 72),
            Map.<String, Integer>entry("minecraft:redstone_ore", 73),
            Map.<String, Integer>entry("minecraft:lit_redstone_ore", 74),
            Map.<String, Integer>entry("minecraft:unlit_redstone_torch", 75),
            Map.<String, Integer>entry("minecraft:redstone_torch", 76),
            Map.<String, Integer>entry("minecraft:stone_button", 77),
            Map.<String, Integer>entry("minecraft:snow_layer", 78),
            Map.<String, Integer>entry("minecraft:ice", 79),
            Map.<String, Integer>entry("minecraft:snow", 80),
            Map.<String, Integer>entry("minecraft:cactus", 81),
            Map.<String, Integer>entry("minecraft:clay", 82),
            Map.<String, Integer>entry("minecraft:reeds", 83),
            Map.<String, Integer>entry("minecraft:jukebox", 84),
            Map.<String, Integer>entry("minecraft:fence", 85),
            Map.<String, Integer>entry("minecraft:pumpkin", 86),
            Map.<String, Integer>entry("minecraft:netherrack", 87),
            Map.<String, Integer>entry("minecraft:soul_sand", 88),
            Map.<String, Integer>entry("minecraft:glowstone", 89),
            Map.<String, Integer>entry("minecraft:portal", 90),
            Map.<String, Integer>entry("minecraft:lit_pumpkin", 91),
            Map.<String, Integer>entry("minecraft:cake", 92),
            Map.<String, Integer>entry("minecraft:unpowered_repeater", 93),
            Map.<String, Integer>entry("minecraft:powered_repeater", 94),
            Map.<String, Integer>entry("minecraft:stained_glass", 95),
            Map.<String, Integer>entry("minecraft:trapdoor", 96),
            Map.<String, Integer>entry("minecraft:monster_egg", 97),
            Map.<String, Integer>entry("minecraft:stonebrick", 98),
            Map.<String, Integer>entry("minecraft:brown_mushroom_block", 99),
            Map.<String, Integer>entry("minecraft:red_mushroom_block", 100),
            Map.<String, Integer>entry("minecraft:iron_bars", 101),
            Map.<String, Integer>entry("minecraft:glass_pane", 102),
            Map.<String, Integer>entry("minecraft:melon_block", 103),
            Map.<String, Integer>entry("minecraft:pumpkin_stem", 104),
            Map.<String, Integer>entry("minecraft:melon_stem", 105),
            Map.<String, Integer>entry("minecraft:vine", 106),
            Map.<String, Integer>entry("minecraft:fence_gate", 107),
            Map.<String, Integer>entry("minecraft:brick_stairs", 108),
            Map.<String, Integer>entry("minecraft:stone_brick_stairs", 109),
            Map.<String, Integer>entry("minecraft:mycelium", 110),
            Map.<String, Integer>entry("minecraft:waterlily", 111),
            Map.<String, Integer>entry("minecraft:nether_brick", 112),
            Map.<String, Integer>entry("minecraft:nether_brick_fence", 113),
            Map.<String, Integer>entry("minecraft:nether_brick_stairs", 114),
            Map.<String, Integer>entry("minecraft:nether_wart", 115),
            Map.<String, Integer>entry("minecraft:enchanting_table", 116),
            Map.<String, Integer>entry("minecraft:brewing_stand", 117),
            Map.<String, Integer>entry("minecraft:cauldron", 118),
            Map.<String, Integer>entry("minecraft:end_portal", 119),
            Map.<String, Integer>entry("minecraft:end_portal_frame", 120),
            Map.<String, Integer>entry("minecraft:end_stone", 121),
            Map.<String, Integer>entry("minecraft:dragon_egg", 122),
            Map.<String, Integer>entry("minecraft:redstone_lamp", 123),
            Map.<String, Integer>entry("minecraft:lit_redstone_lamp", 124),
            Map.<String, Integer>entry("minecraft:double_wooden_slab", 125),
            Map.<String, Integer>entry("minecraft:wooden_slab", 126),
            Map.<String, Integer>entry("minecraft:cocoa", 127),
            Map.<String, Integer>entry("minecraft:sandstone_stairs", 128),
            Map.<String, Integer>entry("minecraft:emerald_ore", 129),
            Map.<String, Integer>entry("minecraft:ender_chest", 130),
            Map.<String, Integer>entry("minecraft:tripwire_hook", 131),
            Map.<String, Integer>entry("minecraft:tripwire", 132),
            Map.<String, Integer>entry("minecraft:emerald_block", 133),
            Map.<String, Integer>entry("minecraft:spruce_stairs", 134),
            Map.<String, Integer>entry("minecraft:birch_stairs", 135),
            Map.<String, Integer>entry("minecraft:jungle_stairs", 136),
            Map.<String, Integer>entry("minecraft:command_block", 137),
            Map.<String, Integer>entry("minecraft:beacon", 138),
            Map.<String, Integer>entry("minecraft:cobblestone_wall", 139),
            Map.<String, Integer>entry("minecraft:flower_pot", 140),
            Map.<String, Integer>entry("minecraft:carrots", 141),
            Map.<String, Integer>entry("minecraft:potatoes", 142),
            Map.<String, Integer>entry("minecraft:wooden_button", 143),
            Map.<String, Integer>entry("minecraft:skull", 144),
            Map.<String, Integer>entry("minecraft:anvil", 145),
            Map.<String, Integer>entry("minecraft:trapped_chest", 146),
            Map.<String, Integer>entry("minecraft:light_weighted_pressure_plate", 147),
            Map.<String, Integer>entry("minecraft:heavy_weighted_pressure_plate", 148),
            Map.<String, Integer>entry("minecraft:unpowered_comparator", 149),
            Map.<String, Integer>entry("minecraft:powered_comparator", 150),
            Map.<String, Integer>entry("minecraft:daylight_detector", 151),
            Map.<String, Integer>entry("minecraft:redstone_block", 152),
            Map.<String, Integer>entry("minecraft:quartz_ore", 153),
            Map.<String, Integer>entry("minecraft:hopper", 154),
            Map.<String, Integer>entry("minecraft:quartz_block", 155),
            Map.<String, Integer>entry("minecraft:quartz_stairs", 156),
            Map.<String, Integer>entry("minecraft:activator_rail", 157),
            Map.<String, Integer>entry("minecraft:dropper", 158),
            Map.<String, Integer>entry("minecraft:stained_hardened_clay", 159),
            Map.<String, Integer>entry("minecraft:stained_glass_pane", 160),
            Map.<String, Integer>entry("minecraft:leaves2", 161),
            Map.<String, Integer>entry("minecraft:log2", 162),
            Map.<String, Integer>entry("minecraft:acacia_stairs", 163),
            Map.<String, Integer>entry("minecraft:dark_oak_stairs", 164),
            Map.<String, Integer>entry("minecraft:slime", 165),
            Map.<String, Integer>entry("minecraft:barrier", 166),
            Map.<String, Integer>entry("minecraft:iron_trapdoor", 167),
            Map.<String, Integer>entry("minecraft:prismarine", 168),
            Map.<String, Integer>entry("minecraft:sea_lantern", 169),
            Map.<String, Integer>entry("minecraft:hay_block", 170),
            Map.<String, Integer>entry("minecraft:carpet", 171),
            Map.<String, Integer>entry("minecraft:hardened_clay", 172),
            Map.<String, Integer>entry("minecraft:coal_block", 173),
            Map.<String, Integer>entry("minecraft:packed_ice", 174),
            Map.<String, Integer>entry("minecraft:double_plant", 175),
            Map.<String, Integer>entry("minecraft:standing_banner", 176),
            Map.<String, Integer>entry("minecraft:wall_banner", 177),
            Map.<String, Integer>entry("minecraft:daylight_detector_inverted", 178),
            Map.<String, Integer>entry("minecraft:red_sandstone", 179),
            Map.<String, Integer>entry("minecraft:red_sandstone_stairs", 180),
            Map.<String, Integer>entry("minecraft:double_stone_slab2", 181),
            Map.<String, Integer>entry("minecraft:stone_slab2", 182),
            Map.<String, Integer>entry("minecraft:spruce_fence_gate", 183),
            Map.<String, Integer>entry("minecraft:birch_fence_gate", 184),
            Map.<String, Integer>entry("minecraft:jungle_fence_gate", 185),
            Map.<String, Integer>entry("minecraft:dark_oak_fence_gate", 186),
            Map.<String, Integer>entry("minecraft:acacia_fence_gate", 187),
            Map.<String, Integer>entry("minecraft:spruce_fence", 188),
            Map.<String, Integer>entry("minecraft:birch_fence", 189),
            Map.<String, Integer>entry("minecraft:jungle_fence", 190),
            Map.<String, Integer>entry("minecraft:dark_oak_fence", 191),
            Map.<String, Integer>entry("minecraft:acacia_fence", 192),
            Map.<String, Integer>entry("minecraft:spruce_door", 193),
            Map.<String, Integer>entry("minecraft:birch_door", 194),
            Map.<String, Integer>entry("minecraft:jungle_door", 195),
            Map.<String, Integer>entry("minecraft:acacia_door", 196),
            Map.<String, Integer>entry("minecraft:dark_oak_door", 197),
            Map.<String, Integer>entry("minecraft:end_rod", 198),
            Map.<String, Integer>entry("minecraft:chorus_plant", 199),
            Map.<String, Integer>entry("minecraft:chorus_flower", 200),
            Map.<String, Integer>entry("minecraft:purpur_block", 201),
            Map.<String, Integer>entry("minecraft:purpur_pillar", 202),
            Map.<String, Integer>entry("minecraft:purpur_stairs", 203),
            Map.<String, Integer>entry("minecraft:purpur_double_slab", 204),
            Map.<String, Integer>entry("minecraft:purpur_slab", 205),
            Map.<String, Integer>entry("minecraft:end_bricks", 206),
            Map.<String, Integer>entry("minecraft:beetroots", 207),
            Map.<String, Integer>entry("minecraft:grass_path", 208),
            Map.<String, Integer>entry("minecraft:end_gateway", 209),
            Map.<String, Integer>entry("minecraft:repeating_command_block", 210),
            Map.<String, Integer>entry("minecraft:chain_command_block", 211),
            Map.<String, Integer>entry("minecraft:frosted_ice", 212),
            Map.<String, Integer>entry("minecraft:magma", 213),
            Map.<String, Integer>entry("minecraft:nether_wart_block", 214),
            Map.<String, Integer>entry("minecraft:red_nether_brick", 215),
            Map.<String, Integer>entry("minecraft:bone_block", 216),
            Map.<String, Integer>entry("minecraft:structure_void", 217),
            Map.<String, Integer>entry("minecraft:observer", 218),
            Map.<String, Integer>entry("minecraft:white_shulker_box", 219),
            Map.<String, Integer>entry("minecraft:orange_shulker_box", 220),
            Map.<String, Integer>entry("minecraft:magenta_shulker_box", 221),
            Map.<String, Integer>entry("minecraft:light_blue_shulker_box", 222),
            Map.<String, Integer>entry("minecraft:yellow_shulker_box", 223),
            Map.<String, Integer>entry("minecraft:lime_shulker_box", 224),
            Map.<String, Integer>entry("minecraft:pink_shulker_box", 225),
            Map.<String, Integer>entry("minecraft:gray_shulker_box", 226),
            Map.<String, Integer>entry("minecraft:silver_shulker_box", 227),
            Map.<String, Integer>entry("minecraft:cyan_shulker_box", 228),
            Map.<String, Integer>entry("minecraft:purple_shulker_box", 229),
            Map.<String, Integer>entry("minecraft:blue_shulker_box", 230),
            Map.<String, Integer>entry("minecraft:brown_shulker_box", 231),
            Map.<String, Integer>entry("minecraft:green_shulker_box", 232),
            Map.<String, Integer>entry("minecraft:red_shulker_box", 233),
            Map.<String, Integer>entry("minecraft:black_shulker_box", 234),
            Map.<String, Integer>entry("minecraft:white_glazed_terracotta", 235),
            Map.<String, Integer>entry("minecraft:orange_glazed_terracotta", 236),
            Map.<String, Integer>entry("minecraft:magenta_glazed_terracotta", 237),
            Map.<String, Integer>entry("minecraft:light_blue_glazed_terracotta", 238),
            Map.<String, Integer>entry("minecraft:yellow_glazed_terracotta", 239),
            Map.<String, Integer>entry("minecraft:lime_glazed_terracotta", 240),
            Map.<String, Integer>entry("minecraft:pink_glazed_terracotta", 241),
            Map.<String, Integer>entry("minecraft:gray_glazed_terracotta", 242),
            Map.<String, Integer>entry("minecraft:silver_glazed_terracotta", 243),
            Map.<String, Integer>entry("minecraft:cyan_glazed_terracotta", 244),
            Map.<String, Integer>entry("minecraft:purple_glazed_terracotta", 245),
            Map.<String, Integer>entry("minecraft:blue_glazed_terracotta", 246),
            Map.<String, Integer>entry("minecraft:brown_glazed_terracotta", 247),
            Map.<String, Integer>entry("minecraft:green_glazed_terracotta", 248),
            Map.<String, Integer>entry("minecraft:red_glazed_terracotta", 249),
            Map.<String, Integer>entry("minecraft:black_glazed_terracotta", 250),
            Map.<String, Integer>entry("minecraft:concrete", 251),
            Map.<String, Integer>entry("minecraft:concrete_powder", 252),
            Map.<String, Integer>entry("minecraft:structure_block", 255)
    );

    public static int getBlockId(String blockName) {
        Integer id = MAP.get(blockName);
        return id == null ? 0 : id;
    }
}