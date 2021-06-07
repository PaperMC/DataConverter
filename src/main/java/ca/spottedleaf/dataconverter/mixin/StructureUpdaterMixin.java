package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.common.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import com.mojang.datafixers.DataFixer;
import net.minecraft.SharedConstants;
import net.minecraft.data.structures.StructureUpdater;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
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
            method = "updateStructure",
            at = @At(
                    target = "Lnet/minecraft/nbt/NbtUtils;update(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/util/datafix/DataFixTypes;Lnet/minecraft/nbt/CompoundTag;I)Lnet/minecraft/nbt/CompoundTag;",
                    value = "INVOKE"
            )
    )
    private static CompoundTag updateStructureData(final DataFixer dataFixer, final DataFixTypes dataFixTypes, final CompoundTag compoundTag,
                                                   final int version) {
        if (dataFixTypes == DataFixTypes.STRUCTURE) {
            return MCDataConverter.convertTag(MCTypeRegistry.STRUCTURE, compoundTag, version, SharedConstants.getCurrentVersion().getWorldVersion());
        }

        return NbtUtils.update(dataFixer, dataFixTypes, compoundTag, version);
    }

}
