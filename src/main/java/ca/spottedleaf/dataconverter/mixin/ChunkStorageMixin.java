package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.util.Version;
import com.mojang.datafixers.DataFixer;
import net.minecraft.SharedConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.chunk.storage.ChunkStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChunkStorage.class)
abstract class ChunkStorageMixin implements AutoCloseable {

    /**
     * @reason DFU is slow :(
     * @author Spottedleaf
     */
    @Redirect(
        method = "upgradeChunkTag",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/datafix/DataFixTypes;update(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/nbt/CompoundTag;II)Lnet/minecraft/nbt/CompoundTag;"
        )
    )
    private CompoundTag redirectUpdate(final DataFixTypes instance, final DataFixer dataFixer, final CompoundTag compoundTag,
                                       final int from, final int to) {
        if (instance != DataFixTypes.CHUNK) {
            throw new IllegalStateException();
        }
        return MCDataConverter.convertTag(MCTypeRegistry.CHUNK, compoundTag, from, to);
    }

    /**
     * @reason DFU is slow :(
     * @author Spottedleaf
     */
    @Redirect(
        method = "upgradeChunkTag",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/datafix/DataFixTypes;updateToCurrentVersion(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/nbt/CompoundTag;I)Lnet/minecraft/nbt/CompoundTag;"
        )
    )
    private CompoundTag redirectUpdateToCurrent(final DataFixTypes instance, final DataFixer dataFixer, final CompoundTag compoundTag,
                                                final int from) {
        if (instance != DataFixTypes.CHUNK) {
            throw new IllegalStateException();
        }
        return MCDataConverter.convertTag(MCTypeRegistry.CHUNK, compoundTag, from, Version.getCurrentVersion());
    }
}
