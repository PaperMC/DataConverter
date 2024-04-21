package ca.spottedleaf.dataconverter.util;

import ca.spottedleaf.dataconverter.types.MapType;
import org.jetbrains.annotations.Nullable;

public final class NamespaceUtil {
    private static final String DEFAULT_NAMESPACE = "minecraft";

    private NamespaceUtil() {
    }

    public static void enforceForPath(final MapType<String> data, final String path) {
        if (data == null) {
            return;
        }

        final String id = data.getString(path);
        if (id != null) {
            final String replace = NamespaceUtil.correctNamespaceOrNull(id);
            if (replace != null) {
                data.setString(path, replace);
            }
        }
    }

    public static String correctNamespace(final String value) {
        if (value == null) {
            return null;
        }
        final String result = parse(value);
        return result != null ? result : value;
    }

    public static String correctNamespaceOrNull(final String value) {
        if (value == null) {
            return null;
        }
        final String correct = correctNamespace(value);
        return correct.equals(value) ? null : correct;
    }

    public static String parse(String value) {
        return parse(value, ':');
    }

    public static String parse(String value, char c) {
        String[] parts = new String[]{DEFAULT_NAMESPACE, value};
        int pos = value.indexOf(c);
        if (pos >= 0) {
            parts[1] = value.substring(pos + 1);
            if (pos >= 1) {
                parts[0] = value.substring(0, pos);
            }
        }
        return validate(parts[0], parts[1]);
    }

    public static @Nullable String validate(String namespace, String path) {
        if (namespace == null || !isValidNamespace(namespace)) return null;
        if (path == null || !isValidPath(path)) return null;
        return namespace + ":" + path;
    }

    public static boolean isValidNamespace(String namespace) {
        for (int i = 0; i < namespace.length(); ++i) {
            if (isValidNamespaceChar(namespace.charAt(i))) continue;
            return false;
        }
        return true;
    }

    public static boolean isValidPath(String path) {
        for (int i = 0; i < path.length(); ++i) {
            if (isValidPathChar(path.charAt(i))) continue;
            return false;
        }
        return true;
    }

    public static boolean isValidPathChar(char c) {
        return c == '_' || c == '-' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9' || c == '/' || c == '.';
    }

    private static boolean isValidNamespaceChar(char c) {
        return c == '_' || c == '-' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9' || c == '.';
    }
}
