package ca.spottedleaf.dataconverter.minecraft.converters.stats;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.helpers.HelperBlockFlatteningV1450;
import ca.spottedleaf.dataconverter.minecraft.converters.itemstack.ConverterFlattenItemStack;
import ca.spottedleaf.dataconverter.minecraft.versions.V1451;
import ca.spottedleaf.dataconverter.types.MapType;
import ca.spottedleaf.dataconverter.types.Types;
import ca.spottedleaf.dataconverter.util.StringUtil;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class ConverterFlattenStats {

    private static final int VERSION = MCVersions.V17W47A;
    private static final int VERSION_STEP = 6;

    private static final Set<String> SPECIAL_OBJECTIVE_CRITERIA = new HashSet<>(
            Set.of(
                    "dummy",
                    "trigger",
                    "deathCount",
                    "playerKillCount",
                    "totalKillCount",
                    "health",
                    "food",
                    "air",
                    "armor",
                    "xp",
                    "level",
                    "killedByTeam.aqua",
                    "killedByTeam.black",
                    "killedByTeam.blue",
                    "killedByTeam.dark_aqua",
                    "killedByTeam.dark_blue",
                    "killedByTeam.dark_gray",
                    "killedByTeam.dark_green",
                    "killedByTeam.dark_purple",
                    "killedByTeam.dark_red",
                    "killedByTeam.gold",
                    "killedByTeam.gray",
                    "killedByTeam.green",
                    "killedByTeam.light_purple",
                    "killedByTeam.red",
                    "killedByTeam.white",
                    "killedByTeam.yellow",
                    "teamkill.aqua",
                    "teamkill.black",
                    "teamkill.blue",
                    "teamkill.dark_aqua",
                    "teamkill.dark_blue",
                    "teamkill.dark_gray",
                    "teamkill.dark_green",
                    "teamkill.dark_purple",
                    "teamkill.dark_red",
                    "teamkill.gold",
                    "teamkill.gray",
                    "teamkill.green",
                    "teamkill.light_purple",
                    "teamkill.red",
                    "teamkill.white",
                    "teamkill.yellow"
            )
    );

    private static final Set<String> SKIP = Set.of(
            "stat.craftItem.minecraft.spawn_egg",
            "stat.useItem.minecraft.spawn_egg",
            "stat.breakItem.minecraft.spawn_egg",
            "stat.pickup.minecraft.spawn_egg",
            "stat.drop.minecraft.spawn_egg"
    );

    private static final Map<String, String> CUSTOM_MAP = Map.ofEntries(
            Map.entry("stat.leaveGame", "minecraft:leave_game"),
            Map.entry("stat.playOneMinute", "minecraft:play_one_minute"),
            Map.entry("stat.timeSinceDeath", "minecraft:time_since_death"),
            Map.entry("stat.sneakTime", "minecraft:sneak_time"),
            Map.entry("stat.walkOneCm", "minecraft:walk_one_cm"),
            Map.entry("stat.crouchOneCm", "minecraft:crouch_one_cm"),
            Map.entry("stat.sprintOneCm", "minecraft:sprint_one_cm"),
            Map.entry("stat.swimOneCm", "minecraft:swim_one_cm"),
            Map.entry("stat.fallOneCm", "minecraft:fall_one_cm"),
            Map.entry("stat.climbOneCm", "minecraft:climb_one_cm"),
            Map.entry("stat.flyOneCm", "minecraft:fly_one_cm"),
            Map.entry("stat.diveOneCm", "minecraft:dive_one_cm"),
            Map.entry("stat.minecartOneCm", "minecraft:minecart_one_cm"),
            Map.entry("stat.boatOneCm", "minecraft:boat_one_cm"),
            Map.entry("stat.pigOneCm", "minecraft:pig_one_cm"),
            Map.entry("stat.horseOneCm", "minecraft:horse_one_cm"),
            Map.entry("stat.aviateOneCm", "minecraft:aviate_one_cm"),
            Map.entry("stat.jump", "minecraft:jump"),
            Map.entry("stat.drop", "minecraft:drop"),
            Map.entry("stat.damageDealt", "minecraft:damage_dealt"),
            Map.entry("stat.damageTaken", "minecraft:damage_taken"),
            Map.entry("stat.deaths", "minecraft:deaths"),
            Map.entry("stat.mobKills", "minecraft:mob_kills"),
            Map.entry("stat.animalsBred", "minecraft:animals_bred"),
            Map.entry("stat.playerKills", "minecraft:player_kills"),
            Map.entry("stat.fishCaught", "minecraft:fish_caught"),
            Map.entry("stat.talkedToVillager", "minecraft:talked_to_villager"),
            Map.entry("stat.tradedWithVillager", "minecraft:traded_with_villager"),
            Map.entry("stat.cakeSlicesEaten", "minecraft:eat_cake_slice"),
            Map.entry("stat.cauldronFilled", "minecraft:fill_cauldron"),
            Map.entry("stat.cauldronUsed", "minecraft:use_cauldron"),
            Map.entry("stat.armorCleaned", "minecraft:clean_armor"),
            Map.entry("stat.bannerCleaned", "minecraft:clean_banner"),
            Map.entry("stat.brewingstandInteraction", "minecraft:interact_with_brewingstand"),
            Map.entry("stat.beaconInteraction", "minecraft:interact_with_beacon"),
            Map.entry("stat.dropperInspected", "minecraft:inspect_dropper"),
            Map.entry("stat.hopperInspected", "minecraft:inspect_hopper"),
            Map.entry("stat.dispenserInspected", "minecraft:inspect_dispenser"),
            Map.entry("stat.noteblockPlayed", "minecraft:play_noteblock"),
            Map.entry("stat.noteblockTuned", "minecraft:tune_noteblock"),
            Map.entry("stat.flowerPotted", "minecraft:pot_flower"),
            Map.entry("stat.trappedChestTriggered", "minecraft:trigger_trapped_chest"),
            Map.entry("stat.enderchestOpened", "minecraft:open_enderchest"),
            Map.entry("stat.itemEnchanted", "minecraft:enchant_item"),
            Map.entry("stat.recordPlayed", "minecraft:play_record"),
            Map.entry("stat.furnaceInteraction", "minecraft:interact_with_furnace"),
            Map.entry("stat.craftingTableInteraction", "minecraft:interact_with_crafting_table"),
            Map.entry("stat.chestOpened", "minecraft:open_chest"),
            Map.entry("stat.sleepInBed", "minecraft:sleep_in_bed"),
            Map.entry("stat.shulkerBoxOpened", "minecraft:open_shulker_box")
    );

    private static final String BLOCK_KEY = "stat.mineBlock";
    private static final String NEW_BLOCK_KEY = "minecraft:mined";

    private static final Map<String, String> ITEM_KEYS = Map.of(
            "stat.craftItem", "minecraft:crafted",
            "stat.useItem", "minecraft:used",
            "stat.breakItem", "minecraft:broken",
            "stat.pickup", "minecraft:picked_up",
            "stat.drop", "minecraft:dropped"
    );

    private static final Map<String, String> ENTITY_KEYS = Map.of(
            "stat.entityKilledBy", "minecraft:killed_by",
            "stat.killEntity", "minecraft:killed"
    );

    private static final Map<String, String> ENTITIES = Map.ofEntries(
            Map.entry("Bat", "minecraft:bat"),
            Map.entry("Blaze", "minecraft:blaze"),
            Map.entry("CaveSpider", "minecraft:cave_spider"),
            Map.entry("Chicken", "minecraft:chicken"),
            Map.entry("Cow", "minecraft:cow"),
            Map.entry("Creeper", "minecraft:creeper"),
            Map.entry("Donkey", "minecraft:donkey"),
            Map.entry("ElderGuardian", "minecraft:elder_guardian"),
            Map.entry("Enderman", "minecraft:enderman"),
            Map.entry("Endermite", "minecraft:endermite"),
            Map.entry("EvocationIllager", "minecraft:evocation_illager"),
            Map.entry("Ghast", "minecraft:ghast"),
            Map.entry("Guardian", "minecraft:guardian"),
            Map.entry("Horse", "minecraft:horse"),
            Map.entry("Husk", "minecraft:husk"),
            Map.entry("Llama", "minecraft:llama"),
            Map.entry("LavaSlime", "minecraft:magma_cube"),
            Map.entry("MushroomCow", "minecraft:mooshroom"),
            Map.entry("Mule", "minecraft:mule"),
            Map.entry("Ozelot", "minecraft:ocelot"),
            Map.entry("Parrot", "minecraft:parrot"),
            Map.entry("Pig", "minecraft:pig"),
            Map.entry("PolarBear", "minecraft:polar_bear"),
            Map.entry("Rabbit", "minecraft:rabbit"),
            Map.entry("Sheep", "minecraft:sheep"),
            Map.entry("Shulker", "minecraft:shulker"),
            Map.entry("Silverfish", "minecraft:silverfish"),
            Map.entry("SkeletonHorse", "minecraft:skeleton_horse"),
            Map.entry("Skeleton", "minecraft:skeleton"),
            Map.entry("Slime", "minecraft:slime"),
            Map.entry("Spider", "minecraft:spider"),
            Map.entry("Squid", "minecraft:squid"),
            Map.entry("Stray", "minecraft:stray"),
            Map.entry("Vex", "minecraft:vex"),
            Map.entry("Villager", "minecraft:villager"),
            Map.entry("VindicationIllager", "minecraft:vindication_illager"),
            Map.entry("Witch", "minecraft:witch"),
            Map.entry("WitherSkeleton", "minecraft:wither_skeleton"),
            Map.entry("Wolf", "minecraft:wolf"),
            Map.entry("ZombieHorse", "minecraft:zombie_horse"),
            Map.entry("PigZombie", "minecraft:zombie_pigman"),
            Map.entry("ZombieVillager", "minecraft:zombie_villager"),
            Map.entry("Zombie", "minecraft:zombie")
    );

    private static final String NEW_CUSTOM_KEY = "minecraft:custom";

    private ConverterFlattenStats() {
    }

    private static String upgradeItem(final String itemName) {
        return ConverterFlattenItemStack.flattenItem(itemName, 0);
    }

    private static String upgradeBlock(final String block) {
        return HelperBlockFlatteningV1450.getNewBlockName(block);
    }

    private static record StatType(String category, String key) {
    }

    private static StatType convertLegacyKey(final String key) {
        if (SKIP.contains(key)) {
            return null;
        }

        final String custom = CUSTOM_MAP.get(key);
        if (custom != null) {
            return new StatType(NEW_CUSTOM_KEY, custom);
        }

        final int i = StringUtil.ordinalIndexOf(key, ".", 2, false);
        if (i < 0) {
            return null;
        }

        final String stat = key.substring(0, i);

        if (BLOCK_KEY.equals(stat)) {
            return new StatType(NEW_BLOCK_KEY, upgradeBlock(key.substring(i + 1).replace('.', ':')));
        }

        final String itemStat = ITEM_KEYS.get(stat);

        if (itemStat != null) {
            final String itemId = key.substring(i + 1).replace('.', ':');
            final String flattenedItem = upgradeItem(itemId);

            return new StatType(itemStat, flattenedItem == null ? itemId : flattenedItem);
        }

        final String entityStat = ENTITY_KEYS.get(stat);
        if (entityStat != null) {
            final String entityId = key.substring(i + 1).replace('.', ':');

            return new StatType(entityStat, ENTITIES.getOrDefault(entityId, entityId));
        }

        return null;
    }

    public static DataConverter<MapType<String>, MapType<String>> makeStatsConverter() {
        return new DataConverter<>(VERSION, VERSION_STEP) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final MapType<String> stats = Types.NBT.createEmptyMap();

                for (final String statKey : data.keys()) {
                    final Number value = data.getNumber(statKey);
                    if (value == null) {
                        continue;
                    }

                    final StatType converted = convertLegacyKey(statKey);

                    if (converted == null) {
                        continue;
                    }

                    stats.getOrCreateMap(converted.category()).setGeneric(converted.key(), value);
                }

                data.clear();
                data.setMap("stats", stats);

                return null;
            }
        };
    }

    public static DataConverter<MapType<String>, MapType<String>> makeObjectiveConverter() {
        return new DataConverter<>(VERSION, VERSION_STEP) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                convertCriteriaName(data, "CriteriaName");

                // We also need to update CriteriaType that is created by the data hook in V1451,
                // otherwise that data hook will overwrite our CriteriaName
                final MapType<String> criteriaType = data.getMap("CriteriaType");
                if (criteriaType != null) {
                    if ("_special".equals(criteriaType.getString("type"))) {
                        convertCriteriaName(criteriaType, "id");
                    }
                }

                return null;
            }

            private void convertCriteriaName(final MapType<String> data, final String key) {
                final String criteriaName = data.getString(key);

                if (criteriaName == null) {
                    return;
                }

                if (SPECIAL_OBJECTIVE_CRITERIA.contains(criteriaName)) {
                    return;
                }

                final StatType converted = convertLegacyKey(criteriaName);
                data.setString(key, converted == null ? "dummy" : V1451.packWithDot(converted.category()) + ":" + V1451.packWithDot(converted.key()));
            }
        };
    }
}
