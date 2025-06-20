package ca.spottedleaf.dataconverter.minecraft.converters.helpers;

import ca.spottedleaf.dataconverter.types.ListType;
import ca.spottedleaf.dataconverter.types.MapType;
import ca.spottedleaf.dataconverter.types.ObjectType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class RenameHelper {

    // assumes no two or more entries are renamed to a single value, otherwise result will be only one of them will win
    // and there is no defined winner in such a case
    public static void renameKeys(final MapType data, final Function<String, String> renamer) {
        if (data == null) {
            return;
        }

        List<String> newKeys = null;
        List<Object> newValues = null;
        boolean needsRename = false;
        for (final String key : data.keys()) {
            final String renamed = renamer.apply(key);
            if (renamed != null) {
                newKeys = new ArrayList<>();
                newValues = new ArrayList<>();
                newValues.add(data.getGeneric(key));
                newKeys.add(renamed);
                data.remove(key);
                needsRename = true;
                break;
            }
        }

        if (!needsRename) {
            return;
        }

        for (final String key : new ArrayList<>(data.keys())) {
            final String renamed = renamer.apply(key);

            if (renamed != null) {
                newValues.add(data.getGeneric(key));
                newKeys.add(renamed);
                data.remove(key);
            }
        }

        // insert new keys
        for (int i = 0, len = newKeys.size(); i < len; ++i) {
            final String key = newKeys.get(i);
            final Object value = newValues.get(i);

            data.setGeneric(key, value);
        }
    }

    // Clobbers anything in toKey if fromKey exists
    public static boolean renameSingle(final MapType data, final String fromKey, final String toKey) {
        if (data == null) {
            return false;
        }

        final Object value = data.getGeneric(fromKey);
        if (value != null) {
            data.remove(fromKey);
            data.setGeneric(toKey, value);
            return true;
        }
        return false;
    }

    public static void renameString(final MapType data, final String key, final Function<String, String> renamer) {
        if (data == null) {
            return;
        }

        final String value = data.getString(key);
        if (value == null) {
            return;
        }

        final String renamed = renamer.apply(value);
        if (renamed == null) {
            return;
        }

        data.setString(key, renamed);
    }

    public static void renameListMapItems(final MapType data, final String listPath, final String mapPath,
                                          final Function<String, String> renamer) {
        if (data == null) {
            return;
        }

        final ListType list = data.getListUnchecked(listPath);
        if (list == null) {
            return;
        }

        for (int i = 0, len = list.size(); i < len; ++i) {
            RenameHelper.renameString(list.getMap(i, null), mapPath, renamer);
        }
    }

    private RenameHelper() {}
}
