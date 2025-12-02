package ca.spottedleaf.dataconverter.mixin;

import ca.spottedleaf.dataconverter.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCDataType;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.util.Version;
import com.mojang.datafixers.DataFixer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.chunk.storage.SimpleRegionStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SimpleRegionStorage.class)
abstract class SimpleRegionStorageMixin implements AutoCloseable {

    @Shadow
    @Final
    private DataFixTypes dataFixType;

    @Unique
    private MCDataType getDataConverterType() {
        if (this.dataFixType == DataFixTypes.CHUNK) {
            return MCTypeRegistry.CHUNK;
        } else if (this.dataFixType == DataFixTypes.ENTITY_CHUNK) {
            return MCTypeRegistry.ENTITY_CHUNK;
        } else if (this.dataFixType == DataFixTypes.POI_CHUNK) {
            return MCTypeRegistry.POI_CHUNK;
        } else {
            throw new UnsupportedOperationException("For " + this.dataFixType.name());
        }
    }

    /**
     * @reason DFU is slow :(
     * @author Spottedleaf
     */
    @Redirect(
        method = "upgradeChunkTag(Lnet/minecraft/nbt/CompoundTag;ILnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/nbt/CompoundTag;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/datafix/DataFixTypes;updateToCurrentVersion(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/nbt/CompoundTag;I)Lnet/minecraft/nbt/CompoundTag;"
        )
    )
    private CompoundTag routeToDataConverter(final DataFixTypes instance, final DataFixer dataFixer, final CompoundTag compoundTag, final int currVer) {
        return MCDataConverter.convertTag(this.getDataConverterType(), compoundTag, currVer, Version.getCurrentVersion());
    }
}
