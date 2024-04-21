package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.walkers.itemstack.DataWalkerItemLists;
import ca.spottedleaf.dataconverter.minecraft.walkers.itemstack.DataWalkerItems;
import ca.spottedleaf.dataconverter.types.MapType;

public final class V3808 {

    private static final int VERSION = MCVersions.V24W04A + 2;

    public static void register() {
        class BodyArmorConverter extends DataConverter<MapType<String>, MapType<String>> {
            private final String path;

            public BodyArmorConverter(final int toVersion, final String path) {
                this(toVersion, 0, path);
            }

            public BodyArmorConverter(final int toVersion, final int versionStep, final String path) {
                super(toVersion, versionStep);

                this.path = path;
            }

            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final MapType<String> prev = data.getMap(this.path);

                if (prev == null) {
                    return null;
                }

                data.remove(this.path);

                data.setMap("body_armor_item", prev);
                data.setFloat("body_armor_drop_chance", 2.0F);

                return null;
            }
        }

        // Step 0
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:horse", new BodyArmorConverter(VERSION, "ArmorItem"));

        MCTypeRegistry.ENTITY.addWalker(VERSION, "minecraft:horse", new DataWalkerItems("SaddleItem"));
        V100.registerEquipment(VERSION, "minecraft:horse");

        // Step 1
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:llama", new BodyArmorConverter(VERSION, 1, "DecorItem"));

        MCTypeRegistry.ENTITY.addWalker(VERSION, 1, "minecraft:llama", new DataWalkerItemLists("Items"));
        MCTypeRegistry.ENTITY.addWalker(VERSION, 1, "minecraft:llama", new DataWalkerItems("SaddleItem"));
        V100.registerEquipment(VERSION, 1, "minecraft:llama");
    }

    private V3808() {}
}
