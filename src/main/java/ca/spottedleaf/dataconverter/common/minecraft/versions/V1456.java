package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;

public final class V1456 {

    protected static final int VERSION = MCVersions.V17W49B + 1;

    protected static byte direction2dTo3d(final byte old) {
        switch (old) {
            case 0:
                return 3;
            case 1:
                return 4;
            case 2:
            default:
                return 2;
            case 3:
                return 5;
        }
    }

    public static void register() {
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:item_frame", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                data.setByte("Facing", direction2dTo3d(data.getByte("Facing")));
                return null;
            }
        });
    }

    private V1456() {}

}
