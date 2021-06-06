package ca.spottedleaf.dataconverter.common.minecraft.walkers.itemstack;

import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.minecraft.walkers.generic.DataWalkerListPaths;
import ca.spottedleaf.dataconverter.common.types.MapType;

public class DataWalkerItemLists extends DataWalkerListPaths<MapType<String>, MapType<String>> {

    public DataWalkerItemLists(final String... paths) {
        super(MCTypeRegistry.ITEM_STACK, paths);
    }
}
