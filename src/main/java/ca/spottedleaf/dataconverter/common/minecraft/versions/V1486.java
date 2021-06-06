package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.converters.entity.ConverterAbstractEntityRename;
import ca.spottedleaf.dataconverter.common.minecraft.converters.itemname.ConverterAbstractItemRename;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.minecraft.walkers.itemstack.DataWalkerItemLists;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

public final class V1486 {

    protected static final int VERSION = MCVersions.V18W19B + 1;

    private static void registerMob(final String id) {
        MCTypeRegistry.ENTITY.addWalker(VERSION, id, new DataWalkerItemLists("ArmorItems", "HandItems"));
    }

    public static final Map<String, String> RENAMED_ENTITY_IDS = ImmutableMap.<String, String>builder()
            .put("minecraft:salmon_mob", "minecraft:salmon")
            .put("minecraft:cod_mob", "minecraft:cod")
            .build();
    public static final Map<String, String> RENAMED_ITEM_IDS = ImmutableMap.<String, String>builder()
            .put("minecraft:salmon_mob_spawn_egg", "minecraft:salmon_spawn_egg")
            .put("minecraft:cod_mob_spawn_egg", "minecraft:cod_spawn_egg")
            .build();


    public static void register() {
        registerMob("minecraft:cod");
        registerMob("minecraft:salmon");

        ConverterAbstractEntityRename.register(VERSION, RENAMED_ENTITY_IDS::get);
        ConverterAbstractItemRename.register(VERSION, RENAMED_ITEM_IDS::get);
    }

    private V1486() {}

}
