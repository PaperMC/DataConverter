package ca.spottedleaf.dataconverter.util;

import net.minecraft.resources.ResourceLocation;

public final class NamespaceUtil {

    private NamespaceUtil() {}

    public static String correctNamespace(final String value) {
        if (value == null) {
            return null;
        }
        final ResourceLocation resourceLocation = ResourceLocation.tryParse(value);
        return resourceLocation != null ? resourceLocation.toString() : value;
    }

    public static String correctNamespaceOrNull(final String value) {
        if (value == null) {
            return null;
        }
        final String correct = correctNamespace(value);
        return correct.equals(value) ? null : correct;
    }
}
