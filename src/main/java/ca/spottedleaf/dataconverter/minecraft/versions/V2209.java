package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.blockname.ConverterAbstractBlockRename;
import ca.spottedleaf.dataconverter.minecraft.converters.itemname.ConverterAbstractItemRename;
import ca.spottedleaf.dataconverter.minecraft.converters.poi.ConverterAbstractPOIRename;

import java.util.Map;

public final class V2209 {

    private static final int VERSION = MCVersions.V19W40A + 1;

    public static void register() {
        final Map<String, String> renamedIds = Map.of(
                "minecraft:bee_hive", "minecraft:beehive"
        );

        ConverterAbstractBlockRename.register(VERSION, renamedIds::get);
        ConverterAbstractItemRename.register(VERSION, renamedIds::get);
        ConverterAbstractPOIRename.register(VERSION, renamedIds::get);
    }

    private V2209() {}
}
