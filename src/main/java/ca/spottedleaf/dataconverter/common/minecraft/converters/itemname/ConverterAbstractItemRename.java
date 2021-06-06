package ca.spottedleaf.dataconverter.common.minecraft.converters.itemname;

import ca.spottedleaf.dataconverter.common.minecraft.converters.helpers.ConverterAbstractStringValueTypeRename;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import java.util.function.Function;

public final class ConverterAbstractItemRename {

    private ConverterAbstractItemRename() {}

    public static void register(final int version, final Function<String, String> renamer) {
        ConverterAbstractStringValueTypeRename.register(version, MCTypeRegistry.ITEM_NAME, renamer);
    }

}
