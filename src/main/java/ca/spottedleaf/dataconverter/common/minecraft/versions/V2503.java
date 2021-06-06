package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;
import com.google.common.collect.ImmutableSet;
import java.util.Set;

public final class V2503 {

    protected static final int VERSION = MCVersions.V1_15_2 + 273;

    private static final Set<String> WALL_BLOCKS = ImmutableSet.of(
            "minecraft:andesite_wall",
            "minecraft:brick_wall",
            "minecraft:cobblestone_wall",
            "minecraft:diorite_wall",
            "minecraft:end_stone_brick_wall",
            "minecraft:granite_wall",
            "minecraft:mossy_cobblestone_wall",
            "minecraft:mossy_stone_brick_wall",
            "minecraft:nether_brick_wall",
            "minecraft:prismarine_wall",
            "minecraft:red_nether_brick_wall",
            "minecraft:red_sandstone_wall",
            "minecraft:sandstone_wall",
            "minecraft:stone_brick_wall"
    );

    private V2503() {}

    private static void changeWallProperty(final MapType<String> properties, final String path) {
        final String property = properties.getString(path);
        if (property != null) {
            properties.setString(path, "true".equals(property) ? "low" : "none");
        }
    }

    public static void register() {
        MCTypeRegistry.BLOCK_STATE.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                if (!WALL_BLOCKS.contains(data.getString("Name"))) {
                    return null;
                }

                final MapType<String> properties = data.getMap("Properties");
                if (properties == null) {
                    return null;
                }

                changeWallProperty(properties, "east");
                changeWallProperty(properties, "west");
                changeWallProperty(properties, "north");
                changeWallProperty(properties, "south");

                return null;
            }
        });
    }
}
