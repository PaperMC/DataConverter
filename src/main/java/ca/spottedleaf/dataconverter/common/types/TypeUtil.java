package ca.spottedleaf.dataconverter.common.types;

public interface TypeUtil {

    public ListType createEmptyList();

    public <K> MapType<K> createEmptyMap();

}
