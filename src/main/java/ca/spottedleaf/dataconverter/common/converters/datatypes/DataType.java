package ca.spottedleaf.dataconverter.common.converters.datatypes;

public abstract class DataType<T, R> {

    public abstract R convert(final T data, final long fromVersion, final long toVersion);

}
