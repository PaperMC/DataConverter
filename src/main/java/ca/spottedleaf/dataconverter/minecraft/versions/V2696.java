package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.blockname.ConverterAbstractBlockRename;
import ca.spottedleaf.dataconverter.minecraft.converters.itemname.ConverterAbstractItemRename;

import java.util.Map;

public final class V2696 {

    private static final int VERSION = MCVersions.V21W07A + 1;

    private static final Map<String, String> RENAMES = Map.ofEntries(
            Map.entry("minecraft:grimstone", "minecraft:deepslate"),
            Map.entry("minecraft:grimstone_slab", "minecraft:cobbled_deepslate_slab"),
            Map.entry("minecraft:grimstone_stairs", "minecraft:cobbled_deepslate_stairs"),
            Map.entry("minecraft:grimstone_wall", "minecraft:cobbled_deepslate_wall"),
            Map.entry("minecraft:polished_grimstone", "minecraft:polished_deepslate"),
            Map.entry("minecraft:polished_grimstone_slab", "minecraft:polished_deepslate_slab"),
            Map.entry("minecraft:polished_grimstone_stairs", "minecraft:polished_deepslate_stairs"),
            Map.entry("minecraft:polished_grimstone_wall", "minecraft:polished_deepslate_wall"),
            Map.entry("minecraft:grimstone_tiles", "minecraft:deepslate_tiles"),
            Map.entry("minecraft:grimstone_tile_slab", "minecraft:deepslate_tile_slab"),
            Map.entry("minecraft:grimstone_tile_stairs", "minecraft:deepslate_tile_stairs"),
            Map.entry("minecraft:grimstone_tile_wall", "minecraft:deepslate_tile_wall"),
            Map.entry("minecraft:grimstone_bricks", "minecraft:deepslate_bricks"),
            Map.entry("minecraft:grimstone_brick_slab", "minecraft:deepslate_brick_slab"),
            Map.entry("minecraft:grimstone_brick_stairs", "minecraft:deepslate_brick_stairs"),
            Map.entry("minecraft:grimstone_brick_wall", "minecraft:deepslate_brick_wall"),
            Map.entry("minecraft:chiseled_grimstone", "minecraft:chiseled_deepslate")
    );

    public static void register() {
        ConverterAbstractItemRename.register(VERSION, RENAMES::get);
        ConverterAbstractBlockRename.register(VERSION, RENAMES::get);
    }

    private V2696() {}
}
