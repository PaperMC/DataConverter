package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.ListType;
import ca.spottedleaf.dataconverter.common.types.MapType;
import ca.spottedleaf.dataconverter.common.types.ObjectType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

public final class V1803 {

    protected static final int VERSION = MCVersions.V1_13_2 + 172;

    private V1803() {}

    public static void register() {
        MCTypeRegistry.ITEM_STACK.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final MapType<String> tag = data.getMap("tag");

                if (tag == null) {
                    return null;
                }

                final MapType<String> display = tag.getMap("display");

                if (display == null) {
                    return null;
                }

                final ListType lore = display.getList("Lore", ObjectType.STRING);
                if (lore == null) {
                    return null;
                }

                for (int i = 0, len = lore.size(); i < len; ++i) {
                    lore.setString(i, Component.Serializer.toJson(new TextComponent(lore.getString(i))));
                }

                return null;
            }
        });
    }

}
