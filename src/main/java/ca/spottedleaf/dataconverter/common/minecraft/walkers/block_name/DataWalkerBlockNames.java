package ca.spottedleaf.dataconverter.common.minecraft.walkers.block_name;

import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.minecraft.walkers.generic.DataWalkerTypePaths;

public final class DataWalkerBlockNames extends DataWalkerTypePaths<Object, Object> {

    public DataWalkerBlockNames(final String... paths) {
        super(MCTypeRegistry.BLOCK_NAME, paths);
    }
}
