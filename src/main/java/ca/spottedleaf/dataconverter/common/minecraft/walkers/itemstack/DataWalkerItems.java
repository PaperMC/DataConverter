package ca.spottedleaf.dataconverter.common.minecraft.walkers.itemstack;

import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.minecraft.walkers.generic.DataWalkerTypePaths;
import ca.spottedleaf.dataconverter.common.types.MapType;

public class DataWalkerItems extends DataWalkerTypePaths<MapType<String>, MapType<String>> {

    public DataWalkerItems(final String... paths) {
        super(MCTypeRegistry.ITEM_STACK, paths);
    }
}
