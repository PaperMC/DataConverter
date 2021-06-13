package ca.spottedleaf.dataconverter.common.types.json;

import ca.spottedleaf.dataconverter.common.types.ListType;
import ca.spottedleaf.dataconverter.common.types.MapType;
import ca.spottedleaf.dataconverter.common.types.TypeUtil;

public final class JsonTypeCompressedUtil implements TypeUtil {

    @Override
    public ListType createEmptyList() {
        return new JsonListType(true);
    }

    @Override
    public MapType<String> createEmptyMap() {
        return new JsonMapType(true);
    }
}
