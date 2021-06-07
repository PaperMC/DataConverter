package ca.spottedleaf.dataconverter.common.converters.datatypes;

import ca.spottedleaf.dataconverter.common.types.MapType;

public interface DataHook<K> {

    public MapType<K> preHook(final MapType<K> data, final long fromVersion, final long toVersion);

    public MapType<K> postHook(final MapType<K> data, final long fromVersion, final long toVersion);

}
