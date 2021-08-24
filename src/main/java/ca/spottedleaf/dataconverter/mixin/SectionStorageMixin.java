package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixer;
import com.mojang.serialization.Dynamic;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.chunk.storage.SectionStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SectionStorage.class)
public abstract class SectionStorageMixin implements AutoCloseable {

    /**
     * Redirects ONLY poi chunk converters to the new dataconverter system. On update,
     * new types should be included here.
     */
    @Redirect(
            method = "readColumn(Lnet/minecraft/world/level/ChunkPos;Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)V",
            at = @At(
                    target = "Lcom/mojang/datafixers/DataFixer;update(Lcom/mojang/datafixers/DSL$TypeReference;Lcom/mojang/serialization/Dynamic;II)Lcom/mojang/serialization/Dynamic;",
                    value = "INVOKE"
            )
    )
    private <T> Dynamic<T> updatePOIData(final DataFixer dataFixer, final DSL.TypeReference type, final Dynamic<T> input,
                                         final int version, final int newVersion) {
        if (type == References.POI_CHUNK) {
            return new Dynamic<>(input.getOps(), (T)MCDataConverter.convertTag(MCTypeRegistry.POI_CHUNK, (CompoundTag)input.getValue(), version, newVersion));
        }

        return dataFixer.update(type, input, version, newVersion);
    }
}
