package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.common.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import com.mojang.datafixers.DataFixer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.storage.DimensionDataStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DimensionDataStorage.class)
public abstract class DimensionDataStorageMixin {

    /**
     * Redirects ONLY saved data converters to the new dataconverter system. On update,
     * new types should be included here.
     */
    @Redirect(
            method = "readTagFromDisk",
            at = @At(
                    target = "Lnet/minecraft/nbt/NbtUtils;update(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/util/datafix/DataFixTypes;Lnet/minecraft/nbt/CompoundTag;II)Lnet/minecraft/nbt/CompoundTag;",
                    value = "INVOKE"
            )
    )
    private CompoundTag updateSavedData(final DataFixer dataFixer, final DataFixTypes dataFixTypes, final CompoundTag compoundTag,
                                                   final int fromVersion, final int toVersion) {
        if (dataFixTypes == DataFixTypes.SAVED_DATA) {
            return MCDataConverter.convertTag(MCTypeRegistry.SAVED_DATA, compoundTag, fromVersion, toVersion);
        }

        return NbtUtils.update(dataFixer, dataFixTypes, compoundTag, fromVersion, toVersion);
    }
}
