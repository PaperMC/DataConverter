package ca.spottedleaf.dataconverter.minecraft.util;

import com.google.gson.JsonObject;
import net.minecraft.util.GsonHelper;

public final class ComponentUtils {

    public static final String EMPTY = createPlainTextComponent("");

    public static String createPlainTextComponent(final String text) {
        final JsonObject ret = new JsonObject();

        ret.addProperty("text", text);

        return GsonHelper.toStableString(ret);
    }

    public static String createTranslatableComponent(final String key) {
        final JsonObject ret = new JsonObject();

        ret.addProperty("translate", key);

        return GsonHelper.toStableString(ret);
    }

    private ComponentUtils() {}
}
