package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.converters.options.ConverterAbstractOptionsRename;
import com.google.common.collect.ImmutableMap;

public final class V2558 {

    protected static final int VERSION = MCVersions.V1_16_PRE2 + 1;

    private V2558() {}

    public static void register() {
        ConverterAbstractOptionsRename.register(VERSION, ImmutableMap.of(
                "key_key.swapHands", "key_key.swapOffhand"
        )::get);
    }
}
