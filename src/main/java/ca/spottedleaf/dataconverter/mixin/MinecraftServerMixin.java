package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftServer.class)
abstract class MinecraftServerMixin {

    /**
     * @reason Init converters early
     * @author Spottedleaf
     */
    @Inject(
        method = "spin",
        at = @At(
            value = "HEAD"
        )
    )
    private static <S extends MinecraftServer> void initConfig(final CallbackInfoReturnable<S> cir) {
        MCTypeRegistry.init();
    }
}
