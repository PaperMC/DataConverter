package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.util.ConvertUtil;
import com.mojang.datafixers.DataFixer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.storage.PlayerDataStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerDataStorage.class)
abstract class PlayerDataStorageMixin {

    /**
     * Redirects ONLY player converters to the new dataconverter system. On update,
     * new types should be included here.
     */
    @Redirect(
            method = "lambda$load$1",
            at = @At(
                    target = "Lnet/minecraft/util/datafix/DataFixTypes;updateToCurrentVersion(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/nbt/CompoundTag;I)Lnet/minecraft/nbt/CompoundTag;",
                    value = "INVOKE"
            )
    )
    private CompoundTag updatePlayerData(final DataFixTypes type, final DataFixer dataFixer, final CompoundTag compoundTag, final int version) {
        return ConvertUtil.convertTag(type, dataFixer, compoundTag, version);
    }
}
