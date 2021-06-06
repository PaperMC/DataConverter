package ca.spottedleaf.dataconverter.common.minecraft.walkers.tile_entity;

import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.minecraft.walkers.generic.DataWalkerTypePaths;
import ca.spottedleaf.dataconverter.common.types.MapType;

public class DataWalkerTileEntities extends DataWalkerTypePaths<MapType<String>, MapType<String>> {

    public DataWalkerTileEntities(final String... paths) {
        super(MCTypeRegistry.TILE_ENTITY, paths);
    }
}
