package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.util.ComponentUtils;
import ca.spottedleaf.dataconverter.types.ObjectType;
import ca.spottedleaf.dataconverter.types.ListType;
import ca.spottedleaf.dataconverter.types.MapType;

public final class V165 {

    private static final int VERSION = MCVersions.V1_9_PRE2;

    public static void register() {
        MCTypeRegistry.TEXT_COMPONENT.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public Object convert(final Object data, final long sourceVersion, final long toVersion) {
                if (!(data instanceof String dataString)) {
                    return data;
                }

                return ComponentUtils.convertFromLenient(dataString);
            }
        });
    }

    private V165() {}
}
