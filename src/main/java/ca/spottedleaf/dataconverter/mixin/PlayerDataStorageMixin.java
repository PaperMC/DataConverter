package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import com.mojang.datafixers.DataFixer;
import net.minecraft.SharedConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.storage.PlayerDataStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerDataStorage.class)
public abstract class PlayerDataStorageMixin {

    /**
     * Redirects ONLY player converters to the new dataconverter system. On update,
     * new types should be included here.
     */
    @Redirect(
            method = "load",
            at = @At(
                    target = "Lnet/minecraft/util/datafix/DataFixTypes;updateToCurrentVersion(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/nbt/CompoundTag;I)Lnet/minecraft/nbt/CompoundTag;",
                    value = "INVOKE"
            )
    )
    private CompoundTag updatePlayerData(final DataFixTypes type, final DataFixer dataFixer, final CompoundTag compoundTag, final int version) {
        if (type == DataFixTypes.PLAYER) {
            return MCDataConverter.convertTag(MCTypeRegistry.PLAYER, compoundTag, version, SharedConstants.getCurrentVersion().getDataVersion().getVersion());
        }

        return type.updateToCurrentVersion(dataFixer, compoundTag, version);
    }
}