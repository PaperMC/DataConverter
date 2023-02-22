package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import net.minecraft.SharedConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.chunk.storage.EntityStorage;
import net.minecraft.world.level.entity.EntityPersistentStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityStorage.class)
public abstract class EntityStorageMixin implements EntityPersistentStorage<Entity> {

    /**
     * @reason DFU is slow :(
     * @author Spottedleaf
     */
    @Overwrite
    private CompoundTag upgradeChunkTag(final CompoundTag entityChunk) {
        final int version = NbtUtils.getDataVersion(entityChunk, -1);
        return MCDataConverter.convertTag(MCTypeRegistry.ENTITY_CHUNK, entityChunk, version, SharedConstants.getCurrentVersion().getDataVersion().getVersion());
    }
}