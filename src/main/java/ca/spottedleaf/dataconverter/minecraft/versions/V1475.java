package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.blockname.ConverterAbstractBlockRename;

import java.util.Map;
import java.util.HashMap;

public final class V1475 {

    private static final int VERSION = MCVersions.V18W10B + 1;

    public static void register() {
        ConverterAbstractBlockRename.register(VERSION, Map.of(
                "minecraft:flowing_water", "minecraft:water",
                "minecraft:flowing_lava", "minecraft:lava"
        )::get);
    }

    private V1475() {
    }
}
