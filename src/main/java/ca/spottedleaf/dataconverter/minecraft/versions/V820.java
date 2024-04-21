package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.itemname.ConverterAbstractItemRename;

import java.util.Map;

public final class V820 {

    protected static final int VERSION = MCVersions.V1_11 + 1;

    public static void register() {
        ConverterAbstractItemRename.register(VERSION, Map.of(
                "minecraft:totem", "minecraft:totem_of_undying"
        )::get);
    }

    private V820() {
    }

}
