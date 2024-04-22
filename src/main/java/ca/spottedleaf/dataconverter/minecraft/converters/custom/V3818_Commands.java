package ca.spottedleaf.dataconverter.minecraft.converters.custom;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.types.ListType;
import ca.spottedleaf.dataconverter.types.MapType;
import ca.spottedleaf.dataconverter.types.ObjectType;
import ca.spottedleaf.dataconverter.util.ExternalDataProvider;
import ca.spottedleaf.dataconverter.util.GsonUtil;
import com.google.gson.*;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.TagStringIO;

import java.io.IOException;
import java.util.Iterator;

public final class V3818_Commands {

    private static final int VERSION = MCVersions.V24W07A + 1;

    public static String toCommandFormat(final CompoundBinaryTag components) {
        final StringBuilder ret = new StringBuilder();
        ret.append('[');
        for (final Iterator<String> iterator = components.keySet().iterator(); iterator.hasNext(); ) {
            final String key = iterator.next();
            ret.append(key);
            ret.append('=');
            ret.append(components.get(key).toString());
            if (iterator.hasNext()) {
                ret.append(',');
            }
        }
        ret.append(']');

        return ret.toString();
    }

    public static JsonElement convertToJson(final BinaryTag tag) {
        // We don't have conversion utilities, but DFU does...

        return new Dynamic<>(ExternalDataProvider.get().nbtOps(), tag).convert(JsonOps.INSTANCE).getValue();
    }

    public static void walkComponent(final JsonElement primitive, final long sourceVersion, final long toVersion) {
        if (!(primitive instanceof JsonObject root)) {
            if (primitive instanceof JsonArray array) {
                for (final JsonElement component : array) {
                    walkComponent(component, sourceVersion, toVersion);
                }
            }
            return;
        }

        final JsonElement clickEventElement = root.get("clickEvent");
        if (clickEventElement instanceof JsonObject clickEvent) {
            final JsonElement actionElement = clickEvent.get("action");
            final JsonElement cmdElement = clickEvent.get("value");
            if (actionElement instanceof JsonPrimitive action && cmdElement instanceof JsonPrimitive cmd) {
                final String actionString = action.getAsString();
                final String cmdString = cmd.getAsString();

                if ((actionString.equals("suggest_command") && cmdString.startsWith("/")) || actionString.equals("run_command")) {
                    final Object res = MCTypeRegistry.DATACONVERTER_CUSTOM_TYPE_COMMAND.convert(
                            cmdString, sourceVersion, toVersion
                    );
                    if (res instanceof String newCmd) {
                        clickEvent.addProperty("value", newCmd);
                    }
                }
            }
        }

        final JsonElement hoverEventElement = root.get("hoverEvent");
        if (hoverEventElement instanceof JsonObject hoverEvent) {
            final JsonElement showText = hoverEvent.get("action");
            if (showText instanceof JsonPrimitive showTextPrimitive && showTextPrimitive.getAsString().equals("show_item")) {
                final JsonElement contentsElement = hoverEvent.get("contents");
                if (contentsElement instanceof JsonObject contents) {
                    final JsonElement idElement = contents.get("id");
                    final JsonElement tagElement = contents.get("tag");

                    if (idElement instanceof JsonPrimitive idPrimitive) {
                        final CompoundBinaryTag.Builder itemNBT = CompoundBinaryTag.builder();
                        itemNBT.putString("id", idPrimitive.getAsString());
                        itemNBT.putInt("Count", 1);

                        if (tagElement instanceof JsonPrimitive tagPrimitive) {
                            try {
                                final CompoundBinaryTag tag = TagStringIO.get().asCompound(tagPrimitive.getAsString());
                                itemNBT.put("tag", tag);
                            } catch (final IOException ignore) {
                            }
                        }

                        final CompoundBinaryTag converted = MCDataConverter.convertTag(
                                MCTypeRegistry.ITEM_STACK, itemNBT.build(), MCVersions.V1_20_4,
                                ExternalDataProvider.get().dataVersion()
                        );

                        contents.remove("tag");

                        contents.addProperty("id", converted.getString("id"));

                        if (converted.get("components") instanceof CompoundBinaryTag componentsTag) {
                            contents.add("components", convertToJson(componentsTag));
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

    private static String walkComponent(final String json, final long sourceVersion, final long toVersion) {
        if (json == null || json.isEmpty()) {
            return json;
        }

        try {
            final JsonElement element = JsonParser.parseString(json);
            walkComponent(element, sourceVersion, toVersion);
            return GsonUtil.toStableString(element);
        } catch (final JsonParseException ex) {
            return json;
        }
    }

    // this is AFTER all the converters for subversion 5, so these run AFTER them
    public static void register_5() {
        // Command is already registered in walker for command blocks
        //todo
//        MCTypeRegistry.DATACONVERTER_CUSTOM_TYPE_COMMAND.addConverter(new DataConverter<>(VERSION, 5) {
//            private static final Supplier<CommandArgumentUpgrader> COMMAND_UPGRADER = Suppliers.memoize(() ->
//                    CommandArgumentUpgrader.upgrader_1_20_4_to_1_20_5(999));
//
//            @Override
//            public Object convert(final Object data, final long sourceVersion, final long toVersion) {
//                if (!(data instanceof String cmd)) {
//                    return null;
//                }
//                // We use startsWith("/") because we aren't supporting WorldEdit style commands,
//                // and passing the context of whether the use supports leading slash would be high effort low return
//                return COMMAND_UPGRADER.get().upgradeCommandArguments(cmd, cmd.startsWith("/"));
//            }
//        });

        // command is not registered in any walkers for books/signs, and we don't want to do that as we would parse
        // the json every walk. instead, we create a one time converter to avoid the additional cost of parsing the json
        // for future updates

        // books
        // note: at this stage, item is converted to components, so we can use the data components type
        MCTypeRegistry.DATA_COMPONENTS.addStructureConverter(new DataConverter<>(VERSION, 5) {
            private static void walkPath(final MapType<String> data, final String path, final long sourceVersion, final long toVersion) {
                final String str = data.getString(path);
                if (str == null) {
                    return;
                }

                final String newStr = walkComponent(str, sourceVersion, toVersion);
                if (newStr != null) {
                    data.setString(path, newStr);
                }
            }

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

                    walkPath(text, "raw", sourceVersion, toVersion);
                    walkPath(text, "filtered", sourceVersion, toVersion);
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
                if (messages != null) {
                    for (int i = 0, len = Math.min(4, messages.size()); i < len; ++i) {
                        messages.setString(i, walkComponent(messages.getString(i), sourceVersion, toVersion));
                    }
                }

                final ListType filteredMessages = text.getList("filtered_messages", ObjectType.STRING);

                if (filteredMessages != null) {
                    for (int i = 0, len = Math.min(4, filteredMessages.size()); i < len; ++i) {
                        filteredMessages.setString(i, walkComponent(filteredMessages.getString(i), sourceVersion, toVersion));
                    }
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
