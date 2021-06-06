package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.converters.entity.ConverterAbstractEntityRename;
import ca.spottedleaf.dataconverter.common.minecraft.converters.itemname.ConverterAbstractItemRename;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.minecraft.walkers.itemstack.DataWalkerItemLists;
import com.google.common.collect.ImmutableMap;

public final class V1928 {

    protected static final int VERSION = MCVersions.V19W04B + 1;

    private V1928() {}

    private static void registerMob(final String id) {
        MCTypeRegistry.ENTITY.addWalker(VERSION, id, new DataWalkerItemLists("ArmorItems", "HandItems"));
    }

    public static void register() {
        ConverterAbstractEntityRename.register(VERSION, ImmutableMap.of(
                "minecraft:illager_beast", "minecraft:ravager"
        )::get);
        ConverterAbstractItemRename.register(VERSION, ImmutableMap.of(
                "minecraft:illager_beast_spawn_egg", "minecraft:ravager_spawn_egg"
        )::get);

        registerMob("minecraft:ravager");
    }
}
