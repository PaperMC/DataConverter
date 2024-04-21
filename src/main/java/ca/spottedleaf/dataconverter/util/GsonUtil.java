package ca.spottedleaf.dataconverter.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class GsonUtil {
    private static final Gson GSON = new Gson();

    public static <T> T fromNullableJson(String string, Class<T> type, boolean lenient) {
        return fromNullableJson(new StringReader(string), type, lenient);
    }

    public static <T> T fromNullableJson(Reader reader, Class<T> type, boolean lenient) {
        try {
            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.setLenient(lenient);
            return (T) GSON.getAdapter(type).read(jsonReader);
        } catch (IOException e) {
            throw new JsonParseException(e);
        }
    }

    public static @NotNull String toStableString(JsonElement json) {
        // This does not actually go to a stable string, just preserving the original calls in case this needs to be a stable string again.
        return GSON.toJson(json);
    }

}
