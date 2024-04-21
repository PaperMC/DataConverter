package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.helpers.ConverterAbstractStringValueTypeRename;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;

import java.util.Map;

public final class V2552 {

    protected static final int VERSION = MCVersions.V20W20B + 15;

    private V2552() {
    }

    public static void register() {
        ConverterAbstractStringValueTypeRename.register(VERSION, MCTypeRegistry.BIOME, Map.of(
                "minecraft:nether", "minecraft:nether_wastes"
        )::get);
    }

}
