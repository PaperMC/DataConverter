package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.walkers.itemstack.DataWalkerItemLists;
import ca.spottedleaf.dataconverter.minecraft.walkers.itemstack.DataWalkerItems;

public final class V3327 {

    private static final int VERSION = MCVersions.V23W06A + 1;

    public static void register() {
        MCTypeRegistry.TILE_ENTITY.addWalker(VERSION, "minecraft:decorated_pot", new DataWalkerItemLists("shards"));
        MCTypeRegistry.TILE_ENTITY.addWalker(VERSION, "minecraft:suspicious_sand", new DataWalkerItems("item"));
    }
}
