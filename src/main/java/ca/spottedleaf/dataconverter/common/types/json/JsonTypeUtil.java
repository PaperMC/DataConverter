package ca.spottedleaf.dataconverter.common.types.json;

import ca.spottedleaf.dataconverter.common.types.ListType;
import ca.spottedleaf.dataconverter.common.types.MapType;
import ca.spottedleaf.dataconverter.common.types.TypeUtil;
import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import java.io.StringReader;

public final class JsonTypeUtil implements TypeUtil {

    @Override
    public ListType createEmptyList() {
        return new JsonListType(false);
    }

    @Override
    public MapType<String> createEmptyMap() {
        return new JsonMapType(false);
    }

    public static <T extends JsonElement> T copyJson(final T from) {
        // This is stupidly inefficient. However, deepCopy() is not exposed in this gson version.
        final String out = from.toString();

        return (T)Streams.parse(new JsonReader(new StringReader(out)));
    }
}
