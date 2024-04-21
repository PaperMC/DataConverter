package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.attributes.ConverterAbstractAttributesRename;
import java.util.Map;

public final class V2523 {

    private static final int VERSION = MCVersions.V20W13B + 2;

    private static final Map<String, String> RENAMES = Map.ofEntries(
            Map.entry("generic.maxHealth", "minecraft:generic.max_health"),
            Map.entry("Max Health", "minecraft:generic.max_health"),
            Map.entry("zombie.spawnReinforcements", "minecraft:zombie.spawn_reinforcements"),
            Map.entry("Spawn Reinforcements Chance", "minecraft:zombie.spawn_reinforcements"),
            Map.entry("horse.jumpStrength", "minecraft:horse.jump_strength"),
            Map.entry("Jump Strength", "minecraft:horse.jump_strength"),
            Map.entry("generic.followRange", "minecraft:generic.follow_range"),
            Map.entry("Follow Range", "minecraft:generic.follow_range"),
            Map.entry("generic.knockbackResistance", "minecraft:generic.knockback_resistance"),
            Map.entry("Knockback Resistance", "minecraft:generic.knockback_resistance"),
            Map.entry("generic.movementSpeed", "minecraft:generic.movement_speed"),
            Map.entry("Movement Speed", "minecraft:generic.movement_speed"),
            Map.entry("generic.flyingSpeed", "minecraft:generic.flying_speed"),
            Map.entry("Flying Speed", "minecraft:generic.flying_speed"),
            Map.entry("generic.attackDamage", "minecraft:generic.attack_damage"),
            Map.entry("generic.attackKnockback", "minecraft:generic.attack_knockback"),
            Map.entry("generic.attackSpeed", "minecraft:generic.attack_speed"),
            Map.entry("generic.armorToughness", "minecraft:generic.armor_toughness")
    );

    public static void register() {
        ConverterAbstractAttributesRename.register(VERSION, RENAMES::get);
    }

    private V2523() {}
}
