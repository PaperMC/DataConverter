package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.walkers.game_event.GameEventListenerWalker;
import ca.spottedleaf.dataconverter.minecraft.walkers.itemstack.DataWalkerItemLists;

public final class V3078 {

    private static final int VERSION = MCVersions.V1_18_2 + 103;

    public static void register() {
        //registerMob("minecraft:frog"); // changed to simple in 1.21.5
        //registerMob("minecraft:tadpole"); // changed to simple in 1.21.5
        MCTypeRegistry.TILE_ENTITY.addWalker(VERSION, "minecraft:sculk_shrieker", new GameEventListenerWalker());
    }

    private V3078() {}
}
