package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.types.ListType;
import ca.spottedleaf.dataconverter.types.MapType;
import ca.spottedleaf.dataconverter.types.TypeUtil;

public final class V4297 {

    private static final int VERSION = MCVersions.V1_21_4 + 108;

    public static void register() {
        MCTypeRegistry.SAVED_DATA_TICKETS.addStructureConverter(new DataConverter<>(VERSION) {
            private static final long INVALID_COORDINATE = ((long)Integer.MIN_VALUE & 0xFFFFFFFFL) | ((long)Integer.MIN_VALUE << 32);

            @Override
            public MapType convert(final MapType root, final long sourceVersion, final long toVersion) {
                final MapType data = root.getMap("data");
                if (data == null) {
                    return null;
                }

                final ListType forced = data.getListUnchecked("Forced");
                if (forced == null) {
                    return null;
                }

                data.remove("Forced");

                final TypeUtil<?> typeUtil = data.getTypeUtil();

                final ListType tickets = typeUtil.createEmptyList();
                data.setList("tickets", tickets);

                for (int i = 0, len = forced.size(); i < len; ++i) {
                    final long coordinate = forced.getLong(i, INVALID_COORDINATE);
                    if (coordinate == INVALID_COORDINATE) {
                        continue;
                    }

                    final MapType ticket = typeUtil.createEmptyMap();
                    tickets.addMap(ticket);

                    ticket.setString("type", "minecraft:forced");
                    ticket.setInt("level", 31);
                    ticket.setLong("ticks_left", 0L);
                    ticket.setLong("chunk_pos", coordinate);
                }

                return null;
            }
        });
    }

    private V4297() {}
}
