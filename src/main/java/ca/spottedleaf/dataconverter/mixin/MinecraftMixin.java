package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
abstract class MinecraftMixin {

    /**
     * @reason Init converters early
     * @author Spottedleaf
     */
    @Inject(
        method = "<init>",
        at = @At(
            value = "RETURN"
        )
    )
    private void initConverters(final CallbackInfo ci) {
        // This causes the game to blow up in the renderer... not sure why, and I don't care to find out.
        //MCTypeRegistry.init();
    }
}
