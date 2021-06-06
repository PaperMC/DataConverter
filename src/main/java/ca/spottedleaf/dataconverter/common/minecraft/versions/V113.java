package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.ObjectType;
import ca.spottedleaf.dataconverter.common.types.ListType;
import ca.spottedleaf.dataconverter.common.types.MapType;

public final class V113 {

    protected static final int VERSION = MCVersions.V15W33C + 1;

    public static void register() {
        // Removes "HandDropChances" and "ArmorDropChances" if they're empty.
        MCTypeRegistry.ENTITY.addStructureConverter(new DataConverter<>(VERSION) {
            private void checkList(final MapType<String> data, final String id, final int requiredLength) {
                final ListType list = data.getList(id, ObjectType.FLOAT);
                if (list != null && list.size() == requiredLength) {
                    for (int i = 0; i < requiredLength; ++i) {
                        if (list.getFloat(i) != 0.0F) {
                            return;
                        }
                    }
                }

                data.remove(id);
            }

            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                this.checkList(data, "HandDropChances", 2);
                this.checkList(data, "ArmorDropChances", 4);
                return null;
            }
        });
    }

    private V113() {}

}
