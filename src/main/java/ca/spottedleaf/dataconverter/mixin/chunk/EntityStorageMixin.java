package ca.spottedleaf.dataconverter.mixin.chunk;

import ca.spottedleaf.dataconverter.common.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import net.minecraft.SharedConstants;
import net.minecraft.nbt.CompoundTag;
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
        final int version = EntityStorage.getVersion(entityChunk);
        return MCDataConverter.convertTag(MCTypeRegistry.ENTITY_CHUNK, entityChunk, version, SharedConstants.getCurrentVersion().getWorldVersion());
    }
}
