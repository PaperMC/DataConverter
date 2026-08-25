package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.util.ConvertUtil;
import com.mojang.datafixers.DataFixer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.chunk.storage.SimpleRegionStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SimpleRegionStorage.class)
abstract class SimpleRegionStorageMixin implements AutoCloseable {

    /**
     * @reason DFU is slow :(
     * @author Spottedleaf
     */
    @Redirect(
        method = "upgradeChunkTag(Lnet/minecraft/nbt/CompoundTag;ILnet/minecraft/nbt/CompoundTag;I)Lnet/minecraft/nbt/CompoundTag;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/datafix/DataFixTypes;update(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/nbt/CompoundTag;II)Lnet/minecraft/nbt/CompoundTag;"
        )
    )
    private CompoundTag routeToDataConverter(final DataFixTypes instance, final DataFixer fixer, final CompoundTag tag,
                                             final int fromVersion, final int toVersion) {
        return ConvertUtil.convertTag(instance, fixer, tag, fromVersion, toVersion);
    }
}
