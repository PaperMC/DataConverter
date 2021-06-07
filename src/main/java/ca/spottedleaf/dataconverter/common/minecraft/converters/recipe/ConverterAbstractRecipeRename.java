package ca.spottedleaf.dataconverter.common.minecraft.converters.recipe;

import ca.spottedleaf.dataconverter.common.minecraft.converters.helpers.ConverterAbstractStringValueTypeRename;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import java.util.function.Function;

public final class ConverterAbstractRecipeRename {

    private ConverterAbstractRecipeRename() {}

    public static void register(final int version, final Function<String, String> renamer) {
        register(version, 0, renamer);
    }

    public static void register(final int version, final int subVersion, final Function<String, String> renamer) {
        ConverterAbstractStringValueTypeRename.register(version, subVersion, MCTypeRegistry.RECIPE, renamer);
    }
}
