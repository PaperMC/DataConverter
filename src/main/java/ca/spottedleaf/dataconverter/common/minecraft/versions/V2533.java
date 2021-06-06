package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.ListType;
import ca.spottedleaf.dataconverter.common.types.MapType;
import ca.spottedleaf.dataconverter.common.types.ObjectType;

public final class V2533 {

    protected static final int VERSION = MCVersions.V20W18A + 1;

    private V2533() {}

    public static void register() {
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:villager", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final ListType attributes = data.getList("Attributes", ObjectType.MAP);

                if (attributes == null) {
                    return null;
                }

                for (int i = 0, len = attributes.size(); i < len; ++i) {
                    final MapType<String> attribute = attributes.getMap(i);

                    if (!"generic.follow_range".equals(attribute.getString("Name"))) {
                        continue;
                    }

                    if (attribute.getDouble("Base") == 16.0) {
                        attribute.setDouble("Base", 48.0);
                    }
                }

                return null;
            }
        });
    }

}
