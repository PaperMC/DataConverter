package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.common.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixer;
import com.mojang.serialization.Dynamic;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LevelStorageSource.class)
public abstract class LevelStorageSourceMixin {

    private static <T> Dynamic<T> updateLevel(final DataFixer dataFixer, final DSL.TypeReference type, final Dynamic<T> input,
                                              final int version, final int newVersion) {
        if (type == References.LEVEL) {
            return new Dynamic<>(input.getOps(), (T)MCDataConverter.convertTag(MCTypeRegistry.LEVEL, (CompoundTag)input.getValue(), version, newVersion));
        }

        return dataFixer.update(type, input, version, newVersion);
    }

    /**
     * Redirects ONLY level converters to the new dataconverter system. On update,
     * new types should be included here.
     */
    @Redirect(
            method = "getDataPacks",
            at = @At(
                    target = "Lcom/mojang/datafixers/DataFixer;update(Lcom/mojang/datafixers/DSL$TypeReference;Lcom/mojang/serialization/Dynamic;II)Lcom/mojang/serialization/Dynamic;",
                    value = "INVOKE"
            )
    )
    private static <T> Dynamic<T> updateLevel_getDataPacks(final DataFixer dataFixer, final DSL.TypeReference type, final Dynamic<T> input,
                                                           final int version, final int newVersion) {
        return updateLevel(dataFixer, type, input, version, newVersion);
    }

    /**
     * Redirects ONLY level converters to the new dataconverter system. On update,
     * new types should be included here.
     */
    @Redirect(
            method = "getLevelData",
            at = @At(
                    target = "Lcom/mojang/datafixers/DataFixer;update(Lcom/mojang/datafixers/DSL$TypeReference;Lcom/mojang/serialization/Dynamic;II)Lcom/mojang/serialization/Dynamic;",
                    value = "INVOKE"
            )
    )
    private static <T> Dynamic<T> updateLevel_getLevelData(final DataFixer dataFixer, final DSL.TypeReference type, final Dynamic<T> input,
                                                           final int version, final int newVersion) {
        return updateLevel(dataFixer, type, input, version, newVersion);
    }

    /**
     * Redirects ONLY level converters to the new dataconverter system. On update,
     * new types should be included here.
     */
    @Redirect(
            method = "levelSummaryReader",
            at = @At(
                    target = "Lcom/mojang/datafixers/DataFixer;update(Lcom/mojang/datafixers/DSL$TypeReference;Lcom/mojang/serialization/Dynamic;II)Lcom/mojang/serialization/Dynamic;",
                    value = "INVOKE"
            )
    )
    private <T> Dynamic<T> updateLevel_levelSummaryReader(final DataFixer dataFixer, final DSL.TypeReference type, final Dynamic<T> input,
                                                          final int version, final int newVersion) {
        return updateLevel(dataFixer, type, input, version, newVersion);
    }

    /**
     * Redirects ONLY world gen settings converters to the new dataconverter system. On update,
     * new types should be included here.
     */
    @Redirect(
            method = "readWorldGenSettings",
            at = @At(
                    target = "Lcom/mojang/datafixers/DataFixer;update(Lcom/mojang/datafixers/DSL$TypeReference;Lcom/mojang/serialization/Dynamic;II)Lcom/mojang/serialization/Dynamic;",
                    value = "INVOKE"
            )
    )
    private static <T> Dynamic<T> updateWorldGenSettings(final DataFixer dataFixer, final DSL.TypeReference type, final Dynamic<T> input,
                                                         final int version, final int newVersion) {
        if (type == References.WORLD_GEN_SETTINGS) {
            return new Dynamic<>(input.getOps(), (T)MCDataConverter.convertTag(MCTypeRegistry.WORLD_GEN_SETTINGS, (CompoundTag)input.getValue(), version, newVersion));
        }

        return dataFixer.update(type, input, version, newVersion);
    }
}
