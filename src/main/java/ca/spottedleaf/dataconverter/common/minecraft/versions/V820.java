package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.converters.itemname.ConverterAbstractItemRename;
import com.google.common.collect.ImmutableMap;

public final class V820 {

    protected static final int VERSION = MCVersions.V1_11 + 1;

    public static void register() {
        ConverterAbstractItemRename.register(VERSION, ImmutableMap.of(
                "minecraft:totem", "minecraft:totem_of_undying"
        )::get);
    }

    private V820() {}

}
