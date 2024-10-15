package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;

public final class V4071 {

    private static final int VERSION = MCVersions.V24W39A + 2;

    private static void registerMob(final String id) {
        V100.registerEquipment(VERSION, 0, id);
    }

    public static void register() {
        registerMob("minecraft:creaking");
        registerMob("minecraft:creaking_transient");

        // minecraft:creaking_heart is a simple tile entity? not sure what the difference is between remainder and optional
    }

    private V4071() {}
}
