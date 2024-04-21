package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.entity.ConverterAbstractEntityRename;
import ca.spottedleaf.dataconverter.minecraft.converters.itemname.ConverterAbstractItemRename;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.walkers.itemstack.DataWalkerItemLists;

import java.util.Map;

public final class V1483 {

    protected static final int VERSION = MCVersions.V18W16A;

    private static void registerMob(final String id) {
        MCTypeRegistry.ENTITY.addWalker(VERSION, id, new DataWalkerItemLists("ArmorItems", "HandItems"));
    }

    public static void register() {
        ConverterAbstractEntityRename.register(VERSION, Map.of(
                "minecraft:puffer_fish", "minecraft:pufferfish"
        )::get);
        ConverterAbstractItemRename.register(VERSION, Map.of(
                "minecraft:puffer_fish_spawn_egg", "minecraft:pufferfish_spawn_egg"
        )::get);

        MCTypeRegistry.ENTITY.copyWalkers(VERSION, "minecraft:puffer_fish", "minecraft:pufferfish");
    }

    private V1483() {
    }

}
