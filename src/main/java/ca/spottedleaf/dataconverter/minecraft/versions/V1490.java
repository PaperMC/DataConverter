package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.blockname.ConverterAbstractBlockRename;
import ca.spottedleaf.dataconverter.minecraft.converters.itemname.ConverterAbstractItemRename;

import java.util.Map;

import java.util.HashMap;

public final class V1490 {

    private static final int VERSION = MCVersions.V18W20A + 1;

    public static void register() {
        ConverterAbstractBlockRename.register(VERSION, Map.of(
                "minecraft:melon_block", "minecraft:melon"
        )::get);
        ConverterAbstractItemRename.register(VERSION, Map.of(
                "minecraft:melon_block", "minecraft:melon",
                "minecraft:melon", "minecraft:melon_slice",
                "minecraft:speckled_melon", "minecraft:glistering_melon_slice"
        )::get);
    }

    private V1490() {
    }
}
