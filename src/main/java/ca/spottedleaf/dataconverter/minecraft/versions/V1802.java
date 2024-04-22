package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.blockname.ConverterAbstractBlockRename;
import ca.spottedleaf.dataconverter.minecraft.converters.itemname.ConverterAbstractItemRename;

import java.util.Map;

public final class V1802 {

    private static final int VERSION = MCVersions.V1_13_2 + 171;

    public static void register() {
        ConverterAbstractBlockRename.register(VERSION, Map.of(
                "minecraft:stone_slab", "minecraft:smooth_stone_slab",
                "minecraft:sign", "minecraft:oak_sign", "minecraft:wall_sign", "minecraft:oak_wall_sign"
        )::get);
        ConverterAbstractItemRename.register(VERSION, Map.of(
                "minecraft:stone_slab", "minecraft:smooth_stone_slab",
                "minecraft:sign", "minecraft:oak_sign"
        )::get);
    }

    private V1802() {
    }
}
