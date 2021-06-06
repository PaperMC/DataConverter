package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.ObjectType;
import ca.spottedleaf.dataconverter.common.types.MapType;
import ca.spottedleaf.dataconverter.common.types.Types;

public final class V806 {

    protected static final int VERSION = MCVersions.V16W36A + 1;

    public static void register() {
        final DataConverter<MapType<String>, MapType<String>> potionWaterUpdater = new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                MapType<String> tag = data.getMap("tag");
                if (tag == null) {
                    tag = Types.NBT.createEmptyMap();
                    data.setMap("tag", tag);
                }

                if (!tag.hasKey("Potion", ObjectType.STRING)) {
                    tag.setString("Potion", "minecraft:water");
                }

                return null;
            }
        };

        MCTypeRegistry.ITEM_STACK.addConverterForId("minecraft:potion", potionWaterUpdater);
        MCTypeRegistry.ITEM_STACK.addConverterForId("minecraft:splash_potion", potionWaterUpdater);
        MCTypeRegistry.ITEM_STACK.addConverterForId("minecraft:lingering_potion", potionWaterUpdater);
        MCTypeRegistry.ITEM_STACK.addConverterForId("minecraft:tipped_arrow", potionWaterUpdater);
    }

    private V806() {}

}
