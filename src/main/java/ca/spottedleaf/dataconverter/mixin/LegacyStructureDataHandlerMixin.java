package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import com.mojang.datafixers.DataFixer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.levelgen.structure.LegacyStructureDataHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LegacyStructureDataHandler.class)
abstract class LegacyStructureDataHandlerMixin {

    /**
     * @reason DFU is slow :(
     * @author Spottedleaf
     */
    @Redirect(
        method = "applyFix",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/datafix/DataFixTypes;update(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/nbt/CompoundTag;II)Lnet/minecraft/nbt/CompoundTag;"
        )
    )
    private CompoundTag routeToDataConverter(final DataFixTypes instance, final DataFixer dataFixer, final CompoundTag compoundTag, final int fromVer, final int toVer) {
        if (instance == DataFixTypes.CHUNK) {
            return MCDataConverter.convertTag(MCTypeRegistry.CHUNK, compoundTag, fromVer, toVer);
        }

        return instance.update(dataFixer, compoundTag, fromVer, toVer);
    }

}
