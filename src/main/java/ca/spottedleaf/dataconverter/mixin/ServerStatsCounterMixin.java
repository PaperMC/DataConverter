package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import com.mojang.datafixers.DataFixer;
import net.minecraft.SharedConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.StatsCounter;
import net.minecraft.util.datafix.DataFixTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerStatsCounter.class)
public abstract class ServerStatsCounterMixin extends StatsCounter {

    /**
     * Redirects ONLY stats converters to the new dataconverter system. On update,
     * new types should be included here.
     */
    @Redirect(
            method = "parseLocal",
            at = @At(
                    target = "Lnet/minecraft/nbt/NbtUtils;update(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/util/datafix/DataFixTypes;Lnet/minecraft/nbt/CompoundTag;I)Lnet/minecraft/nbt/CompoundTag;",
                    value = "INVOKE"
            )
    )
    private CompoundTag updateStatsData(final DataFixer dataFixer, final DataFixTypes dataFixTypes, final CompoundTag compoundTag,
                                        final int version) {
        if (dataFixTypes == DataFixTypes.STATS) {
            return MCDataConverter.convertTag(MCTypeRegistry.STATS, compoundTag, version, SharedConstants.getCurrentVersion().getWorldVersion());
        }

        return NbtUtils.update(dataFixer, dataFixTypes, compoundTag, version);
    }
}
