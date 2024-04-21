package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.blockname.ConverterAbstractBlockRename;
import ca.spottedleaf.dataconverter.minecraft.converters.itemname.ConverterAbstractItemRename;

import java.util.Map;
import java.util.HashMap;

public final class V2680 {

    private static final int VERSION = MCVersions.V1_16_5 + 94;

    public static void register() {
        ConverterAbstractItemRename.register(VERSION, Map.of(
                "minecraft:grass_path", "minecraft:dirt_path"
        )::get);
        ConverterAbstractBlockRename.registerAndFixJigsaw(VERSION, Map.of(
                "minecraft:grass_path", "minecraft:dirt_path"
        )::get);
    }

    private V2680() {}
}
