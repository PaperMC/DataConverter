package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.helpers.RenameHelper;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.types.MapType;

import java.util.Map;

public final class V3566 {

    private static final int VERSION = MCVersions.V1_20_1 + 101;

    public static void register() {
        MCTypeRegistry.SAVED_DATA_SCOREBOARD.addStructureConverter(new DataConverter<>(VERSION) {

            private static final Map<String, String> SLOT_RENAMES = Map.ofEntries(
                    Map.entry("slot_0", "list"),
                    Map.entry("slot_1", "sidebar"),
                    Map.entry("slot_2", "below_name"),
                    Map.entry("slot_3", "sidebar.team.black"),
                    Map.entry("slot_4", "sidebar.team.dark_blue"),
                    Map.entry("slot_5", "sidebar.team.dark_green"),
                    Map.entry("slot_6", "sidebar.team.dark_aqua"),
                    Map.entry("slot_7", "sidebar.team.dark_red"),
                    Map.entry("slot_8", "sidebar.team.dark_purple"),
                    Map.entry("slot_9", "sidebar.team.gold"),
                    Map.entry("slot_10", "sidebar.team.gray"),
                    Map.entry("slot_11", "sidebar.team.dark_gray"),
                    Map.entry("slot_12", "sidebar.team.blue"),
                    Map.entry("slot_13", "sidebar.team.green"),
                    Map.entry("slot_14", "sidebar.team.aqua"),
                    Map.entry("slot_15", "sidebar.team.red"),
                    Map.entry("slot_16", "sidebar.team.light_purple"),
                    Map.entry("slot_17", "sidebar.team.yellow"),
                    Map.entry("slot_18", "sidebar.team.white")
            );

            @Override
            public MapType<String> convert(final MapType<String> root, final long sourceVersion, final long toVersion) {
                final MapType<String> data = root.getMap("data");
                if (data == null) {
                    return null;
                }

                RenameHelper.renameKeys(data.getMap("DisplaySlots"), SLOT_RENAMES::get);

                return null;
            }
        });
    }

    private V3566() {}
}
