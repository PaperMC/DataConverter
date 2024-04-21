package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.types.ListType;
import ca.spottedleaf.dataconverter.types.MapType;
import ca.spottedleaf.dataconverter.types.ObjectType;

import java.util.Map;

public final class V2523 {

    protected static final int VERSION = MCVersions.V20W13B + 2;

    private static final Map<String, String> RENAMES = Map.ofEntries(
            Map.entry("generic.maxHealth", "generic.max_health"),
            Map.entry("Max Health", "generic.max_health"),
            Map.entry("zombie.spawnReinforcements", "zombie.spawn_reinforcements"),
            Map.entry("Spawn Reinforcements Chance", "zombie.spawn_reinforcements"),
            Map.entry("horse.jumpStrength", "horse.jump_strength"),
            Map.entry("Jump Strength", "horse.jump_strength"),
            Map.entry("generic.followRange", "generic.follow_range"),
            Map.entry("Follow Range", "generic.follow_range"),
            Map.entry("generic.knockbackResistance", "generic.knockback_resistance"),
            Map.entry("Knockback Resistance", "generic.knockback_resistance"),
            Map.entry("generic.movementSpeed", "generic.movement_speed"),
            Map.entry("Movement Speed", "generic.movement_speed"),
            Map.entry("generic.flyingSpeed", "generic.flying_speed"),
            Map.entry("Flying Speed", "generic.flying_speed"),
            Map.entry("generic.attackDamage", "generic.attack_damage"),
            Map.entry("generic.attackKnockback", "generic.attack_knockback"),
            Map.entry("generic.attackSpeed", "generic.attack_speed"),
            Map.entry("generic.armorToughness", "generic.armor_toughness")
    );

    private V2523() {
    }

    private static void updateName(final MapType<String> data, final String path) {
        if (data == null) {
            return;
        }

        final String name = data.getString(path);
        if (name != null) {
            final String renamed = RENAMES.get(name);
            if (renamed != null) {
                data.setString(path, renamed);
            }
        }
    }

    public static void register() {
        final DataConverter<MapType<String>, MapType<String>> entityConverter = new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final ListType attributes = data.getList("Attributes", ObjectType.MAP);

                if (attributes == null) {
                    return null;
                }

                for (int i = 0, len = attributes.size(); i < len; ++i) {
                    updateName(attributes.getMap(i), "Name");
                }

                return null;
            }
        };

        MCTypeRegistry.ENTITY.addStructureConverter(entityConverter);
        MCTypeRegistry.PLAYER.addStructureConverter(entityConverter);

        MCTypeRegistry.ITEM_STACK.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final ListType attributes = data.getList("AttributeModifiers", ObjectType.MAP);

                if (attributes == null) {
                    return null;
                }

                for (int i = 0, len = attributes.size(); i < len; ++i) {
                    updateName(attributes.getMap(i), "AttributeName");
                }

                return null;
            }
        });
    }

}
