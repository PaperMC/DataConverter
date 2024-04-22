package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.advancements.ConverterAbstractAdvancementsRename;

import java.util.Map;
import java.util.HashMap;

public final class V2846 {

    private static final int VERSION = MCVersions.V21W44A + 1;

    public static void register() {
        ConverterAbstractAdvancementsRename.register(VERSION, Map.of(
                "minecraft:husbandry/play_jukebox_in_meadows", "minecraft:adventure/play_jukebox_in_meadows",
                "minecraft:adventure/caves_and_cliff", "minecraft:adventure/fall_from_world_height",
                "minecraft:adventure/ride_strider_in_overworld_lava", "minecraft:nether/ride_strider_in_overworld_lava"
        )::get);
    }

    private V2846() {}
}
