package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.blockname.ConverterAbstractBlockRename;
import ca.spottedleaf.dataconverter.minecraft.converters.itemname.ConverterAbstractItemRename;

import java.util.Map;

public final class V2691 {

    private static final int VERSION = MCVersions.V21W05A + 1;

    private static final Map<String, String> RENAMES = Map.of(
            "minecraft:waxed_copper", "minecraft:waxed_copper_block",
            "minecraft:oxidized_copper_block", "minecraft:oxidized_copper",
            "minecraft:weathered_copper_block", "minecraft:weathered_copper",
            "minecraft:exposed_copper_block", "minecraft:exposed_copper"
    );

    public static void register() {
        ConverterAbstractItemRename.register(VERSION, RENAMES::get);
        ConverterAbstractBlockRename.register(VERSION, RENAMES::get);
    }

    private V2691() {}
}
