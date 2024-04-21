package ca.spottedleaf.dataconverter.util;

import com.mojang.serialization.DynamicOps;
import net.kyori.adventure.nbt.BinaryTag;
import org.jetbrains.annotations.NotNull;

public interface ExternalDataProvider {

    static @NotNull ExternalDataProvider get() {
        return null;
    }

    @NotNull DynamicOps<BinaryTag> nbtOps();

}
