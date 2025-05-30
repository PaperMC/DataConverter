package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.walkers.generic.WalkerUtils;
import ca.spottedleaf.dataconverter.types.ListType;
import ca.spottedleaf.dataconverter.types.MapType;
import ca.spottedleaf.dataconverter.types.ObjectType;
import ca.spottedleaf.dataconverter.types.TypeUtil;
import ca.spottedleaf.dataconverter.types.Types;
import ca.spottedleaf.dataconverter.types.nbt.NBTMapType;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.mojang.logging.LogUtils;
import net.minecraft.nbt.TagParser;
import org.slf4j.Logger;

public final class V4290 {

    public static final int VERSION = MCVersions.V1_21_4 + 101;

    private static final Logger LOGGER = LogUtils.getLogger();

    // note: we need to convert the hover component here, so that the walker can properly walk over
    //       the legacy hover event
    // note: this is not done by Vanilla, and they probably fail to correctly convert nested components
    //       here (as the converter to convert from JSON is ordered before the conversion of legacy hover)
    // note: the legacy hover event may contain legacy item data, but there's not really anything we can do about it.
    //       previous versions of the game never walked and converted this data, it was always left to the
    //       users to do it (except in the case that DataConverter was used when converting from 1.20.4,
    //       as we have the custom command walker).
    //       As a result, the item data could be from any version prior to 1.21.4.
    //       There's no way we can correctly convert it in all cases, the best we can do is just
    //       assume that it's 1.21.4 compatible - which is what the code in 1.21.4 was doing when parsing
    //       hover events.
    // note: this function does not need to be recursive, as the conversion process itself is recursive -
    //       the returned text component will be walked with the walker below
    private static void convertLegacyHover(final TypeUtil<?> typeUtil, final MapType textComponent) {
        final MapType hoverEvent = textComponent.getMap("hoverEvent");
        if (hoverEvent == null) {
            return;
        }

        final Object legacyValue = hoverEvent.getGeneric("value");
        if (legacyValue == null) {
            // nothing to do here
            return;
        }

        switch (hoverEvent.getString("action", "")) {
            case "show_text": {
                // value -> another text component; all we need to do is just move it to contents
                hoverEvent.remove("value");
                hoverEvent.setGeneric("contents", legacyValue);
                break;
            }
            case "show_item": {
                // value -> snbt of serialized item
                hoverEvent.remove("value");
                if (!(legacyValue instanceof String legacyItemStr)) {
                    LOGGER.error("Legacy HoverEvent with action=show_item has invalid value, expected string: " + legacyValue);
                    break;
                }

                final MapType legacyItem;
                try {
                    legacyItem = new NBTMapType(TagParser.parseCompoundFully(legacyItemStr));
                } catch (final Exception ex) {
                    LOGGER.error("Failed to parse SNBT for legacy item HoverEvent: " + legacyItemStr, ex);
                    break;
                }

                final MapType newContents = typeUtil.createEmptyMap();
                // note: blindly take precedence over non-legacy data
                hoverEvent.setMap("contents", newContents);

                final String id = legacyItem.getString("id");
                if (id != null) {
                    newContents.setString("id", id);
                }

                final Number count = legacyItem.getNumber("count");
                if (count != null) {
                    newContents.setGeneric("count", count);
                }

                final MapType components = legacyItem.getMap("components");
                if (components != null) {
                    newContents.setMap("components", components.copy());
                }

                break;
            }
            case "show_entity": {
                // value -> snbt of {name:<string json text component>,type:<string>,id:<dashed uuid>}
                hoverEvent.remove("value");
                if (!(legacyValue instanceof String legacyEntityStr)) {
                    LOGGER.error("Legacy HoverEvent with action=show_entity has invalid value, expected string: " + legacyValue);
                    break;
                }

                final MapType legacyEntity;
                try {
                    legacyEntity = new NBTMapType(TagParser.parseCompoundFully(legacyEntityStr));
                } catch (final Exception ex) {
                    LOGGER.error("Failed to parse SNBT for legacy entity HoverEvent: " + legacyEntityStr, ex);
                    break;
                }

                final MapType newContents = typeUtil.createEmptyMap();
                // note: blindly take precedence over non-legacy data
                hoverEvent.setMap("contents", newContents);

                final String name = legacyEntity.getString("name");
                if (name != null) {
                    newContents.setString("name", name);
                }
                final String type = legacyEntity.getString("type");
                if (type != null) {
                    newContents.setString("type", type);
                }
                final String id = legacyEntity.getString("id");
                if (id != null) {
                    newContents.setString("id", id);
                }

                break;
            }
            // default: do nothing
        }
    }

    public static void register() {
        MCTypeRegistry.TEXT_COMPONENT.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public Object convert(final Object data, final long sourceVersion, final long toVersion) {
                if (data instanceof ListType) {
                    // will be iterated by walker
                    return null;
                }
                if (data instanceof MapType mapType) {
                    // (probably) iterated by walker
                    convertLegacyHover(mapType.getTypeUtil(), mapType);
                    return null;
                }
                if (data instanceof Number number) {
                    // text component parsed as list of numbers, which was then iterated by walker
                    // we need to convert to correct type, which is string
                    return number.toString();
                }
                if (data instanceof Boolean bool) {
                    // text component parsed as list of booleans, which was then iterated by walker
                    // we need to convert to correct type, which is string
                    return bool.toString();
                }

                if (!(data instanceof String unparsedJson)) {
                    throw new IllegalArgumentException("Wrong type for text component: " + data);
                }

                try {
                    final JsonElement json = JsonParser.parseString(unparsedJson);

                    if (!json.isJsonNull()) {
                        final Object generic = Types.JSON.convertFromBaseToGeneric(json, Types.NBT);
                        final Object ret = switch (generic) {
                            case Number nt -> nt.toString(); // json may parse as integer, need to convert to string
                            case Boolean bt -> bt.toString(); // json may parse as boolean, need to convert to string
                            case String s -> s; // simple string component
                            case ListType lt -> lt; // list of text components
                            case MapType ct -> ct; // complex text component
                            case byte[] bt -> throw new IllegalStateException("Unexpected byte[] output from JsonTypeUtil");
                            case int[] it -> throw new IllegalStateException("Unexpected int[] output from JsonTypeUtil");
                            case long[] lt -> throw new IllegalStateException("Unexpected long[] output from JsonTypeUtil");
                            // null is handled by isJsonNull()
                            default -> throw new IllegalStateException("Unknown nbt type: " + generic);
                        };

                        if (ret instanceof MapType mapType) {
                            convertLegacyHover(Types.NBT, mapType);
                        }
                        return ret;
                    }
                } catch (final JsonParseException ex) {
                    LOGGER.error("Failed to convert json to nbt: " + unparsedJson, ex);
                }

                return null;
            }
        });

        MCTypeRegistry.TEXT_COMPONENT.addStructureWalker(VERSION, (final Object input, final long fromVersion, final long toVersion) -> {
            if (input instanceof ListType listType) {
                WalkerUtils.convert(MCTypeRegistry.TEXT_COMPONENT, listType, fromVersion, toVersion);
            } else if (input instanceof MapType root) {
                WalkerUtils.convertList(MCTypeRegistry.TEXT_COMPONENT, root, "extra", fromVersion, toVersion);
                WalkerUtils.convert(MCTypeRegistry.TEXT_COMPONENT, root, "separator", fromVersion, toVersion);

                final MapType hoverEvent = root.getMap("hoverEvent");
                if (hoverEvent != null) {
                    switch (hoverEvent.getString("action", "")) {
                        case "show_text": {
                            WalkerUtils.convert(MCTypeRegistry.TEXT_COMPONENT, hoverEvent, "contents", fromVersion, toVersion);
                            break;
                        }
                        case "show_item": {
                            if (hoverEvent.hasKey("contents", ObjectType.STRING)) {
                                WalkerUtils.convert(MCTypeRegistry.ITEM_NAME, hoverEvent, "contents", fromVersion, toVersion);
                            } else {
                                WalkerUtils.convert(MCTypeRegistry.ITEM_STACK, hoverEvent, "contents", fromVersion, toVersion);
                            }
                            break;
                        }
                        case "show_entity": {
                            WalkerUtils.convert(MCTypeRegistry.ENTITY_NAME, hoverEvent, "type", fromVersion, toVersion);
                            WalkerUtils.convert(MCTypeRegistry.TEXT_COMPONENT, hoverEvent, "name", fromVersion, toVersion);
                            break;
                        }
                        // default: do nothing
                    }
                }
            } // else: should only be string
            return null;
        });
    }

    private V4290() {}
}
