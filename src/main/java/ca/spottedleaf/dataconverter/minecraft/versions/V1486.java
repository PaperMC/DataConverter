package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.entity.ConverterAbstractEntityRename;
import ca.spottedleaf.dataconverter.minecraft.converters.itemname.ConverterAbstractItemRename;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.walkers.itemstack.DataWalkerItemLists;

import java.util.Map;

public final class V1486 {

    private static final int VERSION = MCVersions.V18W19B + 1;

    public static final Map<String, String> RENAMED_ENTITY_IDS = Map.of(
            "minecraft:salmon_mob", "minecraft:salmon",
            "minecraft:cod_mob", "minecraft:cod"
    );
    public static final Map<String, String> RENAMED_ITEM_IDS = Map.of(
            "minecraft:salmon_mob_spawn_egg", "minecraft:salmon_spawn_egg",
            "minecraft:cod_mob_spawn_egg", "minecraft:cod_spawn_egg"
    );


    public static void register() {
        MCTypeRegistry.ENTITY.copyWalkers(VERSION, "minecraft:cod_mob", "minecraft:cod");
        MCTypeRegistry.ENTITY.copyWalkers(VERSION, "minecraft:salmon_mob", "minecraft:salmon");

        ConverterAbstractEntityRename.register(VERSION, RENAMED_ENTITY_IDS::get);
        ConverterAbstractItemRename.register(VERSION, RENAMED_ITEM_IDS::get);
    }

    private V1486() {
    }
}
