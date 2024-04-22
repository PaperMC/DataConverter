package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.itemname.ConverterAbstractItemRename;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.walkers.itemstack.DataWalkerItemLists;

import java.util.Map;

public final class V1800 {

    private static final int VERSION = MCVersions.V1_13_2 + 169;

    public static final Map<String, String> RENAMED_ITEM_IDS = Map.of(
            "minecraft:cactus_green", "minecraft:green_dye",
            "minecraft:rose_red", "minecraft:red_dye",
            "minecraft:dandelion_yellow", "minecraft:yellow_dye"
    );

    private static void registerMob(final String id) {
        V100.registerEquipment(VERSION, id);
    }

    public static void register() {
        ConverterAbstractItemRename.register(VERSION, RENAMED_ITEM_IDS::get);

        registerMob("minecraft:panda");
        MCTypeRegistry.ENTITY.addWalker(VERSION, "minecraft:pillager", new DataWalkerItemLists("Inventory"));
        V100.registerEquipment(VERSION, "minecraft:pillager");
    }

    private V1800() {}
}
