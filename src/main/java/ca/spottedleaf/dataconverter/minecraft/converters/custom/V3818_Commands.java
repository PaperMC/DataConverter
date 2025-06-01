package ca.spottedleaf.dataconverter.minecraft.converters.custom;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCDataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.util.Version;
import ca.spottedleaf.dataconverter.types.ListType;
import ca.spottedleaf.dataconverter.types.MapType;
import ca.spottedleaf.dataconverter.types.ObjectType;
import ca.spottedleaf.dataconverter.types.TypeUtil;
import ca.spottedleaf.dataconverter.types.Types;
import ca.spottedleaf.dataconverter.util.CommandArgumentUpgrader;
import com.google.common.base.Suppliers;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.logging.LogUtils;
import net.minecraft.SharedConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.TagParser;
import net.minecraft.util.GsonHelper;
import org.slf4j.Logger;
import java.util.Iterator;
import java.util.function.Supplier;

public final class V3818_Commands {

    private static final int VERSION = MCVersions.V24W07A + 1;

    private static final boolean DISABLE_COMMAND_CONVERTER = Boolean.getBoolean("Paper.DisableCommandConverter");

    private static final Logger LOGGER = LogUtils.getLogger();

    public static String toCommandFormat(final CompoundTag components) {
        final StringBuilder ret = new StringBuilder();
        ret.append('[');
        for (final Iterator<String> iterator = components.keySet().iterator(); iterator.hasNext();) {
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

    public static JsonElement convertToJson(final Tag tag) {
        return Types.NBT.convertBaseToBase(tag, Types.JSON);
    }

    public static void walkComponent(final JsonElement primitive) {
        if (!(primitive instanceof JsonObject root)) {
            if (primitive instanceof JsonArray array) {
                for (final JsonElement component : array) {
                    walkComponent(component);
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
                    try {
                        final Object res = MCDataConverter.convert(
                            MCTypeRegistry.DATACONVERTER_CUSTOM_TYPE_COMMAND, cmdString, MCVersions.V1_20_4, Version.getCurrentVersion()
                        );
                        if (res instanceof String newCmd) {
                            clickEvent.addProperty("value", newCmd);
                        }
                    } catch (final Exception ex) {
                        LOGGER.error("Failed to convert command '" + cmdString + "'", ex);
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
                        final CompoundTag itemNBT = new CompoundTag();
                        itemNBT.putString("id", idPrimitive.getAsString());
                        itemNBT.putInt("Count", 1);

                        if (tagElement instanceof JsonPrimitive tagPrimitive) {
                            try {
                                final CompoundTag tag = TagParser.parseCompoundFully(tagPrimitive.getAsString());
                                itemNBT.put("tag", tag);
                            } catch (final CommandSyntaxException ignore) {}
                        }

                        final CompoundTag converted = MCDataConverter.convertTag(
                            MCTypeRegistry.ITEM_STACK, itemNBT, MCVersions.V1_20_4,
                            Version.getCurrentVersion()
                        );

                        contents.remove("tag");

                        contents.addProperty("id", converted.getStringOr("id", ""));

                        if (converted.contains("components")) {
                            contents.add("components", convertToJson(converted.getCompoundOrEmpty("components")));
                        }
                    }
                }
                final JsonElement valueElement = hoverEvent.get("value");
                if (valueElement instanceof JsonPrimitive valuePrimitive) {
                    try {
                        final CompoundTag itemNBT = TagParser.parseCompoundFully(valuePrimitive.getAsString());
                        if (itemNBT.contains("id")) {
                            final boolean explicitCount = itemNBT.contains("Count");
                            if (!explicitCount) {
                                itemNBT.putInt("Count", 1);
                            }
                            final CompoundTag converted = MCDataConverter.convertTag(
                                MCTypeRegistry.ITEM_STACK, itemNBT, MCVersions.V1_20_4,
                                Version.getCurrentVersion()
                            );

                            hoverEvent.remove("value");

                            final JsonObject contents = new JsonObject();
                            hoverEvent.add("contents", contents);

                            contents.addProperty("id", converted.getStringOr("id", ""));
                            if (explicitCount) {
                                contents.addProperty("count", converted.getIntOr("count", 0));
                            }

                            if (converted.contains("components")) {
                                contents.add("components", convertToJson(converted.getCompoundOrEmpty("components")));
                            }
                        }
                    } catch (final CommandSyntaxException ignore) {}
                }
            }
        }

        final JsonElement extra = root.get("extra");
        if (extra instanceof JsonArray array) {
            for (final JsonElement component : array) {
                walkComponent(component);
            }
        }
    }

    private static String walkComponent(final String json) {
        if (json == null || json.isEmpty()) {
            return json;
        }

        try {
            final JsonElement element = JsonParser.parseString(json);
            walkComponent(element);
            return GsonHelper.toStableString(element);
        } catch (final JsonParseException ex) {
            return json;
        } catch (final Exception ex) {
            LOGGER.error("Failed to convert text component '" + json + "'", ex);
            return json;
        }
    }

    // this is AFTER all the converters for subversion 5, so these run AFTER them
    public static void register_5() {
        if (DISABLE_COMMAND_CONVERTER) {
            return;
        }
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
                try {
                    return COMMAND_UPGRADER.get().upgradeCommandArguments(cmd, cmd.startsWith("/"));
                } catch (final Exception ex) {
                    LOGGER.error("Failed to convert command '" + cmd + "'", ex);
                    return null;
                }
            }
        });

        // command is not registered in any walkers for books/signs, and we don't want to do that as we would parse
        // the json every walk. instead, we create a one time converter to avoid the additional cost of parsing the json
        // for future updates

        // books
        // note: at this stage, item is converted to components, so we can use the data components type
        MCTypeRegistry.DATA_COMPONENTS.addStructureConverter(new DataConverter<>(VERSION, 5) {
            private static void walkPath(final MapType data, final String path) {
                final String str = data.getString(path);
                if (str == null) {
                    return;
                }

                final String newStr = walkComponent(str);
                if (newStr != null) {
                    data.setString(path, newStr);
                }
            }

            private static void walkBookContent(final MapType data, final String path) {
                if (data == null) {
                    return;
                }

                final MapType content = data.getMap(path);
                if (content == null) {
                    return;
                }

                final ListType pages = content.getList("pages", ObjectType.MAP);
                if (pages == null) {
                    return;
                }

                for (int i = 0, len = pages.size(); i < len; ++i) {
                    final MapType text = pages.getMap(i);

                    walkPath(text, "raw");
                    walkPath(text, "filtered");
                }
            }

            @Override
            public MapType convert(final MapType data, final long sourceVersion, final long toVersion) {
                walkBookContent(data, "minecraft:written_book_content");
                return null;
            }
        });

        // signs

        final DataConverter<MapType, MapType> signTileConverter = new DataConverter<>(VERSION, 5) {
            private static void walkText(final MapType data, final String path) {
                if (data == null) {
                    return;
                }

                final MapType text = data.getMap(path);
                if (text == null) {
                    return;
                }

                final ListType messages = text.getList("messages", ObjectType.STRING);
                if (messages != null) {
                    for (int i = 0, len = Math.min(4, messages.size()); i < len; ++i) {
                        messages.setString(i, walkComponent(messages.getString(i)));
                    }
                }

                final ListType filteredMessages = text.getList("filtered_messages", ObjectType.STRING);

                if (filteredMessages != null) {
                    for (int i = 0, len = Math.min(4, filteredMessages.size()); i < len; ++i) {
                        filteredMessages.setString(i, walkComponent(filteredMessages.getString(i)));
                    }
                }
            }

            @Override
            public MapType convert(final MapType data, final long sourceVersion, final long toVersion) {
                walkText(data, "front_text");
                walkText(data, "back_text");
                return null;
            }
        };

        MCTypeRegistry.TILE_ENTITY.addConverterForId("minecraft:sign", signTileConverter);
        MCTypeRegistry.TILE_ENTITY.addConverterForId("minecraft:hanging_sign", signTileConverter);
    }
}
