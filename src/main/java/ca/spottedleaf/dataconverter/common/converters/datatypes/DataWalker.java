package ca.spottedleaf.dataconverter.common.converters.datatypes;

import ca.spottedleaf.dataconverter.common.types.MapType;

public interface DataWalker<K> {

    public void walk(final MapType<K> data, final long fromVersion, final long toVersion);

}
