package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import net.minecraft.SharedConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.storage.ChunkStorage;
import net.minecraft.world.level.levelgen.structure.LegacyStructureDataHandler;
import net.minecraft.world.level.storage.DimensionDataStorage;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import java.util.function.Supplier;

@Mixin(ChunkStorage.class)
public abstract class ChunkStorageMixin implements AutoCloseable {

    @Shadow
    @Nullable
    private LegacyStructureDataHandler legacyStructureHandler;

    /**
     * @reason DFU is slow :(
     * @author Spottedleaf
     */
    @Overwrite
    public CompoundTag upgradeChunkTag(final ResourceKey<Level> resourceKey, final Supplier<DimensionDataStorage> supplier, CompoundTag compoundTag) {
        final int fromVersion = ChunkStorage.getVersion(compoundTag);
        if (fromVersion < 1493) {
            compoundTag = MCDataConverter.convertTag(MCTypeRegistry.CHUNK, compoundTag, fromVersion, 1493);
            if (compoundTag.getCompound("Level").getBoolean("hasLegacyStructureData")) {
                if (this.legacyStructureHandler == null) {
                    this.legacyStructureHandler = LegacyStructureDataHandler.getLegacyStructureHandler(resourceKey, supplier.get());
                }

                compoundTag = this.legacyStructureHandler.updateFromLegacy(compoundTag);
            }
        }

        // add in dimension for converters
        compoundTag.getCompound("Level").putString("__dimension", resourceKey.location().toString());
        compoundTag = MCDataConverter.convertTag(MCTypeRegistry.CHUNK, compoundTag, Math.max(1493, fromVersion), SharedConstants.getCurrentVersion().getWorldVersion());
        if (fromVersion < SharedConstants.getCurrentVersion().getWorldVersion()) {
            compoundTag.putInt("DataVersion", SharedConstants.getCurrentVersion().getWorldVersion());
        }

        compoundTag.getCompound("Level").remove("__dimension"); // remove temp value for converters
        return compoundTag;
    }

}
