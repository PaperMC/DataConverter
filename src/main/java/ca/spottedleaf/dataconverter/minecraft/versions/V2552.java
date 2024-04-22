package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.helpers.ConverterAbstractStringValueTypeRename;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;

import java.util.Map;
import java.util.HashMap;

public final class V2552 {

    private static final int VERSION = MCVersions.V20W20B + 15;

    public static void register() {
        ConverterAbstractStringValueTypeRename.register(VERSION, MCTypeRegistry.BIOME, Map.of(
                "minecraft:nether", "minecraft:nether_wastes"
        )::get);
    }

    private V2552() {}
}
