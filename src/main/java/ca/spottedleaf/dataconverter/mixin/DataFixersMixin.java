package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.minecraft.ReplacedDataFixerUpper;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.DataFixerBuilder;
import net.minecraft.util.datafix.DataFixers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.util.concurrent.Executor;

@Mixin(DataFixers.class)
public abstract class DataFixersMixin {

    @Redirect(
            method = "createFixerUpper",
            at = @At(
                    target = "Lcom/mojang/datafixers/DataFixerBuilder;build(Ljava/util/concurrent/Executor;)Lcom/mojang/datafixers/DataFixer;",
                    value = "INVOKE"
            )
    )
    private static DataFixer redirectToDataconverter(final DataFixerBuilder instance, final Executor executor) {
        final DataFixer wrap = instance.build((final Runnable run) -> {}); // don't (pre) build, we aren't using them

        return new ReplacedDataFixerUpper(wrap);
    }
}
