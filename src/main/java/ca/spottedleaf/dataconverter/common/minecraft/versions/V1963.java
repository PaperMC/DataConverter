package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.ListType;
import ca.spottedleaf.dataconverter.common.types.MapType;
import ca.spottedleaf.dataconverter.common.types.ObjectType;

public final class V1963 {

    protected static final int VERSION = MCVersions.V1_14_2;

    private V1963() {}

    public static void register() {
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:villager", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final ListType gossips = data.getList("Gossips", ObjectType.MAP);
                if (gossips == null) {
                    return null;
                }

                for (int i = 0; i < gossips.size();) {
                    final MapType<String> gossip = gossips.getMap(i);
                    if ("golem".equals(gossip.getString("Type"))) {
                        gossips.remove(i);
                        continue;
                    }

                    ++i;
                }

                return null;
            }
        });
    }

}
