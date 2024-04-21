package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import com.mojang.datafixers.DataFixer;
import net.minecraft.SharedConstants;
import net.minecraft.data.structures.StructureUpdater;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.datafix.DataFixTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StructureUpdater.class)
public abstract class StructureUpdaterMixin {

    /**
     * Redirects ONLY structure converters to the new dataconverter system. On update,
     * new types should be included here.
     */
    @Redirect(
            method = "update",
            at = @At(
                    target = "Lnet/minecraft/util/datafix/DataFixTypes;updateToCurrentVersion(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/nbt/CompoundTag;I)Lnet/minecraft/nbt/CompoundTag;",
                    value = "INVOKE"
            )
    )
    private static CompoundTag updateStructureData(final DataFixTypes type, final DataFixer dataFixer, final CompoundTag compoundTag,
                                                   final int version) {
        if (type == DataFixTypes.STRUCTURE) {
            return MCDataConverter.convertTag(MCTypeRegistry.STRUCTURE, compoundTag, version, SharedConstants.getCurrentVersion().getDataVersion().getVersion());
        }

        return type.updateToCurrentVersion(dataFixer, compoundTag, version);
    }
}