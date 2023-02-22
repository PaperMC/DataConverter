package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.SharedConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.storage.ChunkStorage;
import net.minecraft.world.level.levelgen.structure.LegacyStructureDataHandler;
import net.minecraft.world.level.storage.DimensionDataStorage;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import java.util.Optional;
import java.util.function.Supplier;

@Mixin(ChunkStorage.class)
public abstract class ChunkStorageMixin implements AutoCloseable {

    @Shadow
    @Nullable
    private LegacyStructureDataHandler legacyStructureHandler;

    @Shadow
    protected abstract LegacyStructureDataHandler getLegacyStructureHandler(ResourceKey<Level> resourceKey, Supplier<DimensionDataStorage> supplier);

    /**
     * @reason DFU is slow :(
     * @author Spottedleaf
     */
    @Overwrite
    public CompoundTag upgradeChunkTag(ResourceKey<Level> resourceKey, Supplier<DimensionDataStorage> supplier, CompoundTag compoundTag, Optional<ResourceKey<Codec<? extends ChunkGenerator>>> optional) {
        int i = ChunkStorage.getVersion(compoundTag);
        if (i < 1493) {
            compoundTag = MCDataConverter.convertTag(MCTypeRegistry.CHUNK, compoundTag, i, 1493);
            if (compoundTag.getCompound("Level").getBoolean("hasLegacyStructureData")) {
                LegacyStructureDataHandler legacyStructureDataHandler = this.getLegacyStructureHandler(resourceKey, supplier);
                compoundTag = legacyStructureDataHandler.updateFromLegacy(compoundTag);
            }
        }

        ChunkStorage.injectDatafixingContext(compoundTag, resourceKey, optional);
        compoundTag = MCDataConverter.convertTag(MCTypeRegistry.CHUNK, compoundTag, Math.max(1493, i), SharedConstants.getCurrentVersion().getDataVersion().getVersion());
        if (i < SharedConstants.getCurrentVersion().getDataVersion().getVersion()) {
            compoundTag.putInt("DataVersion", SharedConstants.getCurrentVersion().getDataVersion().getVersion());
        }

        compoundTag.remove("__context");
        return compoundTag;
    }
}