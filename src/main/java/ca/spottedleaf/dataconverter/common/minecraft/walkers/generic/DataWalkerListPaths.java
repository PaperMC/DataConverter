package ca.spottedleaf.dataconverter.common.minecraft.walkers.generic;

import ca.spottedleaf.dataconverter.common.converters.datatypes.DataType;
import ca.spottedleaf.dataconverter.common.converters.datatypes.DataWalker;
import ca.spottedleaf.dataconverter.common.types.ListType;
import ca.spottedleaf.dataconverter.common.types.MapType;

public class DataWalkerListPaths<T, R> implements DataWalker<String> {

    protected final DataType<T, R> type;
    protected final String[] paths;

    public DataWalkerListPaths(final DataType<T, R> type, final String... paths) {
        this.type = type;
        this.paths = paths;
    }

    @Override
    public final void walk(final MapType<String> data, final long fromVersion, final long toVersion) {
        final DataType<T, R> type = this.type;
        for (final String path : this.paths) {
            final ListType list = data.getListUnchecked(path);
            if (list == null) {
                continue;
            }

            for (int i = 0, len = list.size(); i < len; ++i) {
                final Object current = list.getGeneric(i);
                final Object converted = type.convert((T)current, fromVersion, toVersion);
                if (converted != null) {
                    list.setGeneric(i, converted);
                }
            }
        }
    }
}
