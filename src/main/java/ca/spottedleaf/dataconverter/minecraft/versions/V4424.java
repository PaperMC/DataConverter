package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.leveldat.ConverterRemoveFeatureFlag;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import java.util.Arrays;
import java.util.HashSet;

public final class V4424 {

    private static final int VERSION = MCVersions.V25W15A + 2;

    public static void register() {
        MCTypeRegistry.LIGHTWEIGHT_LEVEL.addStructureConverter(new ConverterRemoveFeatureFlag(VERSION, new HashSet<>(
            Arrays.asList(
                "minecraft:locator_bar"
            )
        )));
    }

    private V4424() {}
}
