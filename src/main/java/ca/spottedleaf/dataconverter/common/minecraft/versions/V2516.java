package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.ListType;
import ca.spottedleaf.dataconverter.common.types.MapType;
import ca.spottedleaf.dataconverter.common.types.ObjectType;

public final class V2516 {

    protected static final int VERSION = MCVersions.V20W12A + 1;

    private V2516() {}

    public static void register() {
        final DataConverter<MapType<String>, MapType<String>> gossipUUIDConverter = new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final ListType gossips = data.getList("Gossips", ObjectType.MAP);

                if (gossips == null) {
                    return null;
                }

                for (int i = 0, len = gossips.size(); i < len; ++i) {
                    V2514.replaceUUIDLeastMost(gossips.getMap(i), "Target", "Target");
                }

                return null;
            }
        };

        MCTypeRegistry.ENTITY.addConverterForId("minecraft:villager", gossipUUIDConverter);
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:zombie_villager", gossipUUIDConverter);
    }
}
