package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.common.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixer;
import com.mojang.serialization.Dynamic;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.util.datafix.fixes.References;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerAdvancements.class)
public class PlayerAdvancementsMixin {

    /**
     * Redirects ONLY advancements converters to the new dataconverter system. On update,
     * new types should be included here.
     */
    @Redirect(
            method = "load",
            at = @At(
                    target = "Lcom/mojang/datafixers/DataFixer;update(Lcom/mojang/datafixers/DSL$TypeReference;Lcom/mojang/serialization/Dynamic;II)Lcom/mojang/serialization/Dynamic;",
                    value = "INVOKE"
            )
    )
    private <T> Dynamic<T> updateAdvancementsData(final DataFixer dataFixer, final DSL.TypeReference type, final Dynamic<T> input,
                                                  final int version, final int newVersion) {
        if (type == References.ADVANCEMENTS) {
            return new Dynamic<>(input.getOps(), (T)MCDataConverter.convertTag(MCTypeRegistry.ADVANCEMENTS, (CompoundTag)input.getValue(), version, newVersion));
        }

        return dataFixer.update(type, input, version, newVersion);
    }
}
