package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.helpers.ConverterAbstractStringValueTypeRename;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.util.NamespaceUtil;

import java.util.Map;

public final class V3084 {

    private static final int VERSION = MCVersions.V22W12A + 2;

    private static final Map<String, String> GAME_EVENT_RENAMES = Map.ofEntries(
                    Map.entry("minecraft:block_press", "minecraft:block_activate"),
                    Map.entry("minecraft:block_switch", "minecraft:block_activate"),
                    Map.entry("minecraft:block_unpress", "minecraft:block_deactivate"),
                    Map.entry("minecraft:block_unswitch", "minecraft:block_deactivate"),
                    Map.entry("minecraft:drinking_finish", "minecraft:drink"),
                    Map.entry("minecraft:elytra_free_fall", "minecraft:elytra_glide"),
                    Map.entry("minecraft:entity_damaged", "minecraft:entity_damage"),
                    Map.entry("minecraft:entity_dying", "minecraft:entity_die"),
                    Map.entry("minecraft:entity_killed", "minecraft:entity_die"),
                    Map.entry("minecraft:mob_interact", "minecraft:entity_interact"),
                    Map.entry("minecraft:ravager_roar", "minecraft:entity_roar"),
                    Map.entry("minecraft:ring_bell", "minecraft:block_change"),
                    Map.entry("minecraft:shulker_close", "minecraft:container_close"),
                    Map.entry("minecraft:shulker_open", "minecraft:container_open"),
                    Map.entry("minecraft:wolf_shaking", "minecraft:entity_shake")
    );

    public static void register() {
        ConverterAbstractStringValueTypeRename.register(VERSION, MCTypeRegistry.GAME_EVENT_NAME, (final String name) -> {
            return GAME_EVENT_RENAMES.get(NamespaceUtil.correctNamespace(name));
        });
    }

    private V3084() {}
}
