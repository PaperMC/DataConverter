package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.converters.stats.ConverterAbstractStatsRename;
import com.google.common.collect.ImmutableMap;

public final class V2710 {

    protected static final int VERSION = MCVersions.V21W15A + 1;

    public static void register() {
        ConverterAbstractStatsRename.register(VERSION, ImmutableMap.of(
                "minecraft:play_one_minute", "minecraft:play_time"
        )::get);
    }

}
