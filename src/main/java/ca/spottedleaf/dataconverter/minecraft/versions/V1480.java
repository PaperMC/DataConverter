package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.blockname.ConverterAbstractBlockRename;
import ca.spottedleaf.dataconverter.minecraft.converters.itemname.ConverterAbstractItemRename;

import java.util.Map;

public final class V1480 {

    private static final int VERSION = MCVersions.V18W14A + 1;

    public static final Map<String, String> RENAMED_IDS = Map.ofEntries(
            Map.entry("minecraft:blue_coral", "minecraft:tube_coral_block"),
            Map.entry("minecraft:pink_coral", "minecraft:brain_coral_block"),
            Map.entry("minecraft:purple_coral", "minecraft:bubble_coral_block"),
            Map.entry("minecraft:red_coral", "minecraft:fire_coral_block"),
            Map.entry("minecraft:yellow_coral", "minecraft:horn_coral_block"),
            Map.entry("minecraft:blue_coral_plant", "minecraft:tube_coral"),
            Map.entry("minecraft:pink_coral_plant", "minecraft:brain_coral"),
            Map.entry("minecraft:purple_coral_plant", "minecraft:bubble_coral"),
            Map.entry("minecraft:red_coral_plant", "minecraft:fire_coral"),
            Map.entry("minecraft:yellow_coral_plant", "minecraft:horn_coral"),
            Map.entry("minecraft:blue_coral_fan", "minecraft:tube_coral_fan"),
            Map.entry("minecraft:pink_coral_fan", "minecraft:brain_coral_fan"),
            Map.entry("minecraft:purple_coral_fan", "minecraft:bubble_coral_fan"),
            Map.entry("minecraft:red_coral_fan", "minecraft:fire_coral_fan"),
            Map.entry("minecraft:yellow_coral_fan", "minecraft:horn_coral_fan"),
            Map.entry("minecraft:blue_dead_coral", "minecraft:dead_tube_coral"),
            Map.entry("minecraft:pink_dead_coral", "minecraft:dead_brain_coral"),
            Map.entry("minecraft:purple_dead_coral", "minecraft:dead_bubble_coral"),
            Map.entry("minecraft:red_dead_coral", "minecraft:dead_fire_coral"),
            Map.entry("minecraft:yellow_dead_coral", "minecraft:dead_horn_coral")
    );

    public static void register() {
        ConverterAbstractBlockRename.register(VERSION, RENAMED_IDS::get);
        ConverterAbstractItemRename.register(VERSION, RENAMED_IDS::get);
    }

    private V1480() {
    }
}
