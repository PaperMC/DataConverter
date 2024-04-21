package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.blockname.ConverterAbstractBlockRename;
import ca.spottedleaf.dataconverter.minecraft.converters.itemname.ConverterAbstractItemRename;

import java.util.Map;

public final class V2690 {

    private static final int VERSION = MCVersions.V21W05A;

    private static final Map<String, String> RENAMES = Map.ofEntries(
            Map.entry("minecraft:weathered_copper_block", "minecraft:oxidized_copper_block"),
            Map.entry("minecraft:semi_weathered_copper_block", "minecraft:weathered_copper_block"),
            Map.entry("minecraft:lightly_weathered_copper_block", "minecraft:exposed_copper_block"),
            Map.entry("minecraft:weathered_cut_copper", "minecraft:oxidized_cut_copper"),
            Map.entry("minecraft:semi_weathered_cut_copper", "minecraft:weathered_cut_copper"),
            Map.entry("minecraft:lightly_weathered_cut_copper", "minecraft:exposed_cut_copper"),
            Map.entry("minecraft:weathered_cut_copper_stairs", "minecraft:oxidized_cut_copper_stairs"),
            Map.entry("minecraft:semi_weathered_cut_copper_stairs", "minecraft:weathered_cut_copper_stairs"),
            Map.entry("minecraft:lightly_weathered_cut_copper_stairs", "minecraft:exposed_cut_copper_stairs"),
            Map.entry("minecraft:weathered_cut_copper_slab", "minecraft:oxidized_cut_copper_slab"),
            Map.entry("minecraft:semi_weathered_cut_copper_slab", "minecraft:weathered_cut_copper_slab"),
            Map.entry("minecraft:lightly_weathered_cut_copper_slab", "minecraft:exposed_cut_copper_slab"),
            Map.entry("minecraft:waxed_semi_weathered_copper", "minecraft:waxed_weathered_copper"),
            Map.entry("minecraft:waxed_lightly_weathered_copper", "minecraft:waxed_exposed_copper"),
            Map.entry("minecraft:waxed_semi_weathered_cut_copper", "minecraft:waxed_weathered_cut_copper"),
            Map.entry("minecraft:waxed_lightly_weathered_cut_copper", "minecraft:waxed_exposed_cut_copper"),
            Map.entry("minecraft:waxed_semi_weathered_cut_copper_stairs", "minecraft:waxed_weathered_cut_copper_stairs"),
            Map.entry("minecraft:waxed_lightly_weathered_cut_copper_stairs", "minecraft:waxed_exposed_cut_copper_stairs"),
            Map.entry("minecraft:waxed_semi_weathered_cut_copper_slab", "minecraft:waxed_weathered_cut_copper_slab"),
            Map.entry("minecraft:waxed_lightly_weathered_cut_copper_slab", "minecraft:waxed_exposed_cut_copper_slab")
    );

    public static void register() {
        ConverterAbstractItemRename.register(VERSION, RENAMES::get);
        ConverterAbstractBlockRename.register(VERSION, RENAMES::get);
    }

    private V2690() {}
}
