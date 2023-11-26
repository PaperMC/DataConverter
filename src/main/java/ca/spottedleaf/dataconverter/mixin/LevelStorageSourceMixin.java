package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import com.mojang.datafixers.DataFixer;
import com.mojang.serialization.Dynamic;
import net.minecraft.SharedConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LevelStorageSource.class)
public abstract class LevelStorageSourceMixin {

    /**
     * Redirects ONLY player converters to the new dataconverter system. On update,
     * new types should be included here.
     */
    @Redirect(
            method = "readLevelDataTagFixed",
            at = @At(
                    target = "Lnet/minecraft/util/datafix/DataFixTypes;updateToCurrentVersion(Lcom/mojang/datafixers/DataFixer;Lcom/mojang/serialization/Dynamic;I)Lcom/mojang/serialization/Dynamic;",
                    value = "INVOKE"
            )
    )
    private static <T> Dynamic<T> updatePlayerData(final DataFixTypes type, final DataFixer dataFixer, final Dynamic<T> input, final int version) {
        if (type == DataFixTypes.PLAYER) {
            return new Dynamic<>(input.getOps(), (T)MCDataConverter.convertTag(MCTypeRegistry.PLAYER, (CompoundTag)input.getValue(), version, SharedConstants.getCurrentVersion().getDataVersion().getVersion()));
        }

        return type.updateToCurrentVersion(dataFixer, input, version);
    }
}
