package ca.spottedleaf.dataconverter.minecraft.converters.custom;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.types.ListType;
import ca.spottedleaf.dataconverter.types.MapType;
import ca.spottedleaf.dataconverter.types.ObjectType;
import ca.spottedleaf.dataconverter.util.CommandArgumentUpgrader;
import com.google.common.base.Suppliers;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.util.function.Supplier;

public final class V3818_Commands {

    private static final int VERSION = MCVersions.V24W07A + 1;

    private static void walkComponent(final JsonElement primitive, final long sourceVersion, final long toVersion) {
        if (!(primitive instanceof JsonObject root)) {
            if (primitive instanceof JsonArray array) {
                for (final JsonElement component : array) {
                    walkComponent(component, sourceVersion, toVersion);
                }
            }
            return;
        }

        final JsonElement clickEventElement = root.getAsJsonObject("clickEvent");
        if (clickEventElement instanceof JsonObject clickEvent) {
            final JsonElement actionElement = clickEvent.get("action");
            if (actionElement instanceof JsonPrimitive action) {
                switch (action.getAsString()) {
                    case "run_command":
                    case "suggest_command": {
                        final JsonElement cmdElement = clickEvent.get("value");
                        if (cmdElement instanceof JsonPrimitive cmd) {
                            final Object res = MCTypeRegistry.DATACONVERTER_CUSTOM_TYPE_COMMAND.convert(
                                    cmd.getAsString(), sourceVersion, toVersion
                            );
                            if (res instanceof String newCmd) {
                                clickEvent.addProperty("value", newCmd);
                            }
                        }
                    }
                }
            }
        }

        final JsonElement extra = root.get("extra");
        if (extra instanceof JsonArray array) {
            for (final JsonElement component : array) {
                walkComponent(component, sourceVersion, toVersion);
            }
        }
    }

    private static void walkComponent(final String json, final long sourceVersion, final long toVersion) {
        if (json == null || json.isEmpty()) {
            return;
        }

        try {
            walkComponent(JsonParser.parseString(json), sourceVersion, toVersion);
        } catch (final JsonParseException ignored) {}
    }

    // this is AFTER all the converters for subversion 5, so these run AFTER them
    public static void register_5() {
        // Command is already registered in walker for command blocks
        MCTypeRegistry.DATACONVERTER_CUSTOM_TYPE_COMMAND.addConverter(new DataConverter<>(VERSION, 5) {
            private static final Supplier<CommandArgumentUpgrader> COMMAND_UPGRADER = Suppliers.memoize(() ->
                    CommandArgumentUpgrader.upgrader_1_20_4_to_1_20_5(999));

            @Override
            public Object convert(final Object data, final long sourceVersion, final long toVersion) {
                if (!(data instanceof String cmd)) {
                    return null;
                }
                // We use startsWith("/") because we aren't supporting WorldEdit style commands,
                // and passing the context of whether the use supports leading slash would be high effort low return
                return COMMAND_UPGRADER.get().upgradeCommandArguments(cmd, cmd.startsWith("/"));
            }
        });

        // command is not registered in any walkers for books/signs, and we don't want to do that as we would parse
        // the json every walk. instead, we create a one time converter to avoid the additional cost of parsing the json
        // for future updates

        // books
        // note: at this stage, item is converted to components, so we can use the data components type
        MCTypeRegistry.DATA_COMPONENTS.addStructureConverter(new DataConverter<>(VERSION, 5) {
            private static void walkBookContent(final MapType<String> data, final String path, final long sourceVersion, final long toVersion) {
                if (data == null) {
                    return;
                }

                final MapType<String> content = data.getMap(path);
                if (content == null) {
                    return;
                }

                final ListType pages = content.getList("pages", ObjectType.MAP);
                if (pages == null) {
                    return;
                }

                for (int i = 0, len = pages.size(); i < len; ++i) {
                    final MapType<String> text = pages.getMap(i);

                    walkComponent(text.getString("raw"), sourceVersion, toVersion);
                    walkComponent(text.getString("filtered"), sourceVersion, toVersion);
                }
            }

            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                walkBookContent(data, "minecraft:writable_book_content", sourceVersion, toVersion);
                walkBookContent(data, "minecraft:written_book_content", sourceVersion, toVersion);
                return null;
            }
        });

        // signs

        final DataConverter<MapType<String>, MapType<String>> signTileConverter = new DataConverter<>(VERSION, 5) {
            private static void walkText(final MapType<String> data, final String path, final long sourceVersion, final long toVersion) {
                if (data == null) {
                    return;
                }

                final MapType<String> text = data.getMap(path);
                if (text == null) {
                    return;
                }

                final ListType messages = text.getList("messages", ObjectType.STRING);

                for (int i = 0, len = Math.min(4, messages.size()); i < len; ++i) {
                    walkComponent(messages.getString(i), sourceVersion, toVersion);
                }

                final ListType filteredMessages = text.getList("filtered_messages", ObjectType.STRING);

                for (int i = 0, len = Math.min(4, filteredMessages.size()); i < len; ++i) {
                    walkComponent(filteredMessages.getString(i), sourceVersion, toVersion);
                }
            }

            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                walkText(data, "front_text", sourceVersion, toVersion);
                walkText(data, "back_text", sourceVersion, toVersion);
                return null;
            }
        };

        MCTypeRegistry.TILE_ENTITY.addConverterForId("minecraft:sign", signTileConverter);
        MCTypeRegistry.TILE_ENTITY.addConverterForId("minecraft:hanging_sign", signTileConverter);
    }
}
