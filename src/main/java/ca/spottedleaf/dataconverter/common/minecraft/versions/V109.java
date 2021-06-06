package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;
import com.google.common.collect.Sets;
import java.util.Set;

public final class V109 {

    protected static final int VERSION = MCVersions.V15W32C + 5;

    public static void register() {
        // Converts health to be in float, and cleans up whatever the hell was going on with HealF and Health...
        MCTypeRegistry.ENTITY.addStructureConverter(new DataConverter<>(VERSION) {
            // Vanilla declares this exact field but leaves it unused. Not sure why, legacy conversion system checked if the ID matched.
            // I'm going to leave it here unused as well, just in case it's needed in the future.
            private final Set<String> ENTITIES = Sets.newHashSet(new String[]{"ArmorStand", "Bat", "Blaze", "CaveSpider", "Chicken", "Cow", "Creeper", "EnderDragon", "Enderman", "Endermite", "EntityHorse", "Ghast", "Giant", "Guardian", "LavaSlime", "MushroomCow", "Ozelot", "Pig", "PigZombie", "Rabbit", "Sheep", "Shulker", "Silverfish", "Skeleton", "Slime", "SnowMan", "Spider", "Squid", "Villager", "VillagerGolem", "Witch", "WitherBoss", "Wolf", "Zombie"});

            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final Number healF = data.getNumber("HealF");
                final Number heal = data.getNumber("Health");

                final float newHealth;

                if (healF != null) {
                    data.remove("HealF");
                    newHealth = healF.floatValue();
                } else {
                    if (heal == null) {
                        return null;
                    }

                    newHealth = heal.floatValue();
                }

                data.setFloat("Health", newHealth);

                return null;
            }
        });
    }

    private V109() {}

}
