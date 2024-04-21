package ca.spottedleaf.dataconverter.minecraft.util;

import ca.spottedleaf.dataconverter.util.GsonUtil;
import com.google.gson.JsonObject;

public final class ComponentUtils {

    public static final String EMPTY = createPlainTextComponent("");

    public static String createPlainTextComponent(final String text) {
        final JsonObject ret = new JsonObject();

        ret.addProperty("text", text);

        return GsonUtil.toStableString(ret);
    }

    public static String createTranslatableComponent(final String key) {
        final JsonObject ret = new JsonObject();

        ret.addProperty("translate", key);

        return GsonUtil.toStableString(ret);
    }

    private ComponentUtils() {
    }
}
