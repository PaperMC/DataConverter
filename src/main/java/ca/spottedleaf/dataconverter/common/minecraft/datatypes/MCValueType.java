package ca.spottedleaf.dataconverter.common.minecraft.datatypes;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.converters.datatypes.DataType;
import java.util.ArrayList;
import java.util.List;

public class MCValueType extends DataType<Object, Object> {

    protected final ArrayList<DataConverter<Object, Object>> converters = new ArrayList<>();
    public final String name;

    public MCValueType(final String name) {
        this.name = name;
    }

    public void addConverter(final DataConverter<Object, Object> converter) {
        this.converters.add(converter);
        this.converters.sort(DataConverter.LOWEST_VERSION_COMPARATOR);
    }

    @Override
    public Object convert(final Object data, final long fromVersion, final long toVersion) {
        Object ret = null;
        final List<DataConverter<Object, Object>> converters = this.converters;

        for (int i = 0, len = converters.size(); i < len; ++i) {
            final DataConverter<Object, Object> converter = converters.get(i);
            final long converterVersion = converter.getEncodedVersion();

            if (converterVersion <= fromVersion) {
                continue;
            }

            if (converterVersion > toVersion) {
                break;
            }

            final Object converted = converter.convert(ret == null ? data : ret, fromVersion, toVersion);
            if (converted != null) {
                ret = converted;
            }
        }

        return ret;
    }
}
