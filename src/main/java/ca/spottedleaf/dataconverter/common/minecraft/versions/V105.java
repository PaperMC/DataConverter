package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.converters.helpers.HelperSpawnEggNameV105;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.ObjectType;
import ca.spottedleaf.dataconverter.common.types.MapType;
import ca.spottedleaf.dataconverter.common.types.Types;

public final class V105 {

    protected static final int VERSION = MCVersions.V15W32C + 1;

    public static void register() {
        MCTypeRegistry.ITEM_STACK.addConverterForId("minecraft:spawn_egg", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                MapType<String> tag = data.getMap("tag");
                if (tag == null) {
                    tag = Types.NBT.createEmptyMap();
                }

                final short damage = data.getShort("Damage");
                if (damage != 0) {
                    data.setShort("Damage", (short)0);
                }

                MapType<String> entityTag = tag.getMap("EntityTag");
                if (entityTag == null) {
                    entityTag = Types.NBT.createEmptyMap();
                }

                if (!entityTag.hasKey("id", ObjectType.STRING)) {
                    final String converted = HelperSpawnEggNameV105.getSpawnNameFromId(damage);
                    if (converted != null) {
                        entityTag.setString("id", converted);
                        tag.setMap("EntityTag", entityTag);
                        data.setMap("tag", tag);
                    }
                }

                return null;
            }
        });
    }

    private V105() {}
}
