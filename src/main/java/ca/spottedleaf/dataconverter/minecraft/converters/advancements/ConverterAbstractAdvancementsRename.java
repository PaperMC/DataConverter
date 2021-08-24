package ca.spottedleaf.dataconverter.minecraft.converters.advancements;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.types.MapType;
import java.util.ArrayList;
import java.util.function.Function;

public final class ConverterAbstractAdvancementsRename {

    private ConverterAbstractAdvancementsRename() {}

    public static void register(final int version, final Function<String, String> renamer) {
        register(version, 0, renamer);
    }

    public static void register(final int version, final int subVersion, final Function<String, String> renamer) {
        MCTypeRegistry.ADVANCEMENTS.addStructureConverter(new DataConverter<>(version, subVersion) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                for (final String key : new ArrayList<>(data.keys())) {
                    final String updated = renamer.apply(key);
                    if (updated != null) {
                        final Object value = data.getGeneric(key);
                        data.remove(key);
                        data.setGeneric(updated, value);
                    }
                }
                return null;
            }
        });
    }

}
