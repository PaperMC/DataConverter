package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.converters.blockname.ConverterAbstractBlockRename;
import ca.spottedleaf.dataconverter.common.minecraft.converters.itemname.ConverterAbstractItemRename;
import com.google.common.collect.ImmutableMap;

public final class V2717 {

    protected static final int VERSION = MCVersions.V1_17_PRE1 + 1;

    public static void register() {
        final ImmutableMap<String, String> rename = ImmutableMap.of(
                "minecraft:azalea_leaves_flowers", "minecraft:flowering_azalea_leaves"
        );
        ConverterAbstractItemRename.register(VERSION, rename::get);
        ConverterAbstractBlockRename.register(VERSION, rename::get);
    }

}
