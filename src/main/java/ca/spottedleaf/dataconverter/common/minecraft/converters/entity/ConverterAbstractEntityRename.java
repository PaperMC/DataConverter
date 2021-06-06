package ca.spottedleaf.dataconverter.common.minecraft.converters.entity;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;
import java.util.function.Function;

public final class ConverterAbstractEntityRename {

    private ConverterAbstractEntityRename() {}

    public static void register(final int version, final Function<String, String> renamer) {
        MCTypeRegistry.ENTITY.addStructureConverter(new DataConverter<>(version) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final String id = data.getString("id");
                if (id == null) {
                    return null;
                }

                final String converted = renamer.apply(id);

                if (converted != null) {
                    data.setString("id", converted);
                }

                return null;
            }
        });
        MCTypeRegistry.ENTITY_NAME.addConverter(new DataConverter<>(version) {
            @Override
            public Object convert(final Object data, final long sourceVersion, final long toVersion) {
                final String ret = (data instanceof String) ? renamer.apply((String)data) : null;
                return ret == data ? null : ret;
            }
        });
    }

}
