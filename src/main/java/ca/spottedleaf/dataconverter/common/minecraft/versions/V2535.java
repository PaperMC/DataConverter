package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.ListType;
import ca.spottedleaf.dataconverter.common.types.MapType;
import ca.spottedleaf.dataconverter.common.types.ObjectType;

public final class V2535 {

    protected static final int VERSION = MCVersions.V20W19A + 1;

    private V2535() {}

    public static void register() {
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:shulker", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                // Mojang uses doubles for whatever reason... rotation is in FLOAT. by using double here
                // the entity load will just ignore rotation and set it to 0...
                final ListType rotation = data.getList("Rotation", ObjectType.FLOAT);

                if (rotation == null || rotation.size() == 0) {
                    return null;
                }

                rotation.setFloat(0, rotation.getFloat(0) - 180.0F);

                return null;
            }
        });
    }
}
