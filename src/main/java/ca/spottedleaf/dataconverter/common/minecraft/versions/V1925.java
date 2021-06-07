package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;
import ca.spottedleaf.dataconverter.common.types.Types;

public final class V1925 {

    protected static final int VERSION = MCVersions.V19W03C + 1;

    public static void register() {
        MCTypeRegistry.SAVED_DATA.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> root, final long sourceVersion, final long toVersion) {
                final MapType<String> data = root.getMap("data");
                if (data == null) {
                    final MapType<String> ret = Types.NBT.createEmptyMap();
                    ret.setMap("data", root);

                    return ret;
                }
                return null;
            }
        });
    }

}
