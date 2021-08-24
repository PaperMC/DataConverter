package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.minecraft.walkers.itemstack.DataWalkerItemLists;
import ca.spottedleaf.dataconverter.common.types.MapType;

public final class V700 {

    protected static final int VERSION = MCVersions.V1_10_2 + 188;

    private static void registerMob(final String id) {
        MCTypeRegistry.ENTITY.addWalker(VERSION, id, new DataWalkerItemLists("ArmorItems", "HandItems"));
    }

    public static void register() {
        MCTypeRegistry.ENTITY.addConverterForId("Guardian", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                if (data.getBoolean("Elder")) {
                    data.setString("id", "ElderGuardian");
                }
                data.remove("Elder");
                return null;
            }
        });

        registerMob("ElderGuardian");
    }

    private V700() {}
}
