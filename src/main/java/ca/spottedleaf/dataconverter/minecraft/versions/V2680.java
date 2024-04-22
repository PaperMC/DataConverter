package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.blockname.ConverterAbstractBlockRename;
import ca.spottedleaf.dataconverter.minecraft.converters.itemname.ConverterAbstractItemRename;

import java.util.Map;

public final class V2680 {

    private static final int VERSION = MCVersions.V1_16_5 + 94;

    public static void register() {
        ConverterAbstractItemRename.register(VERSION, Map.of(
                "minecraft:grass_path", "minecraft:dirt_path"
        )::get);
        ConverterAbstractBlockRename.register(VERSION, Map.of(
                "minecraft:grass_path", "minecraft:dirt_path"
        )::get);
    }

    private V2680() {
    }
}
