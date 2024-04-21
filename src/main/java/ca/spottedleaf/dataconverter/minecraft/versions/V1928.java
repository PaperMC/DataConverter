package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.entity.ConverterAbstractEntityRename;
import ca.spottedleaf.dataconverter.minecraft.converters.itemname.ConverterAbstractItemRename;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.walkers.itemstack.DataWalkerItemLists;

import java.util.Map;

public final class V1928 {

    protected static final int VERSION = MCVersions.V19W04B + 1;

    private V1928() {
    }

    private static void registerMob(final String id) {
        MCTypeRegistry.ENTITY.addWalker(VERSION, id, new DataWalkerItemLists("ArmorItems", "HandItems"));
    }

    public static void register() {
        ConverterAbstractEntityRename.register(VERSION, Map.of(
                "minecraft:illager_beast", "minecraft:ravager"
        )::get);
        ConverterAbstractItemRename.register(VERSION, Map.of(
                "minecraft:illager_beast_spawn_egg", "minecraft:ravager_spawn_egg"
        )::get);

        registerMob("minecraft:ravager");
    }
}
