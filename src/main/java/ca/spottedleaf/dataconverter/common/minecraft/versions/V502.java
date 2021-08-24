package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.converters.itemname.ConverterAbstractItemRename;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;
import net.minecraft.util.datafix.schemas.NamespacedSchema;
import java.util.concurrent.ThreadLocalRandom;

public final class V502 {

    protected static final int VERSION = MCVersions.V16W20A + 1;

    public static void register() {
        ConverterAbstractItemRename.register(VERSION, (final String name) -> {
            return "minecraft:cooked_fished".equals(name) ? "minecraft:cooked_fish" : null;
        });
        MCTypeRegistry.ENTITY.addConverterForId("Zombie", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                if (!data.getBoolean("IsVillager")) {
                    return null;
                }

                data.remove("IsVillager");

                if (data.hasKey("ZombieType")) {
                    return null;
                }

                int type = data.getInt("VillagerProfession", -1);
                // Vanilla doesn't remove the profession tag, so we don't!
                if (type < 0 || type >= 6) {
                    type = ThreadLocalRandom.current().nextInt(6);
                }

                data.setInt("ZombieType", type);

                return null;
            }
        });
    }

    private V502() {}

}
