package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import com.mojang.datafixers.DataFixer;
import net.minecraft.SharedConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.levelgen.structure.StructureCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StructureCheck.class)
public abstract class StructureCheckMixin {

    /**
     * Redirects ONLY chunk converters to the new dataconverter system. On update,
     * new types should be included here.
     */
    @Redirect(
            method = "tryLoadFromStorage",
            at = @At(
                    target = "Lnet/minecraft/nbt/NbtUtils;update(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/util/datafix/DataFixTypes;Lnet/minecraft/nbt/CompoundTag;I)Lnet/minecraft/nbt/CompoundTag;",
                    value = "INVOKE"
            )
    )
    private CompoundTag updatePartialChunk(final DataFixer dataFixer, final DataFixTypes dataFixTypes, final CompoundTag compoundTag,
                                           final int version) {
        if (dataFixTypes == DataFixTypes.CHUNK) {
            return MCDataConverter.convertTag(MCTypeRegistry.CHUNK, compoundTag, version, SharedConstants.getCurrentVersion().getWorldVersion());
        }

        return NbtUtils.update(dataFixer, dataFixTypes, compoundTag, version);
    }
}
