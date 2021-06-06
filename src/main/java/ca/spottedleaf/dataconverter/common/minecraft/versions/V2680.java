package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.converters.blockname.ConverterAbstractBlockRename;
import ca.spottedleaf.dataconverter.common.minecraft.converters.itemname.ConverterAbstractItemRename;
import com.google.common.collect.ImmutableMap;

public final class V2680 {

    protected static final int VERSION = MCVersions.V1_16_5 + 94;

    public static void register() {
        ConverterAbstractItemRename.register(VERSION, ImmutableMap.of(
                "minecraft:grass_path", "minecraft:dirt_path"
        )::get);
        ConverterAbstractBlockRename.registerAndFixJigsaw(VERSION, ImmutableMap.of(
                "minecraft:grass_path", "minecraft:dirt_path"
        )::get);
    }

}
