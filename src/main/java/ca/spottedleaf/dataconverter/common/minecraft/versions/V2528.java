package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.converters.blockname.ConverterAbstractBlockRename;
import ca.spottedleaf.dataconverter.common.minecraft.converters.itemname.ConverterAbstractItemRename;
import com.google.common.collect.ImmutableMap;

public final class V2528 {

    protected static final int VERSION = MCVersions.V20W16A + 2;

    private V2528() {}

    public static void register() {
        ConverterAbstractItemRename.register(VERSION, ImmutableMap.of(
                "minecraft:soul_fire_torch", "minecraft:soul_torch",
                "minecraft:soul_fire_lantern", "minecraft:soul_lantern"
        )::get);
        ConverterAbstractBlockRename.register(VERSION, ImmutableMap.of(
                "minecraft:soul_fire_torch", "minecraft:soul_torch",
                "minecraft:soul_fire_wall_torch", "minecraft:soul_wall_torch",
                "minecraft:soul_fire_lantern", "minecraft:soul_lantern"
        )::get);
    }
}
