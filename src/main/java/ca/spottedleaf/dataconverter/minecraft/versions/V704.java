package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.hooks.DataHookEnforceNamespacedID;
import ca.spottedleaf.dataconverter.minecraft.walkers.generic.DataWalkerTypePaths;
import ca.spottedleaf.dataconverter.minecraft.walkers.itemstack.DataWalkerItemLists;
import ca.spottedleaf.dataconverter.minecraft.walkers.item_name.DataWalkerItemNames;
import ca.spottedleaf.dataconverter.minecraft.walkers.itemstack.DataWalkerItems;
import ca.spottedleaf.dataconverter.minecraft.walkers.generic.WalkerUtils;
import ca.spottedleaf.dataconverter.types.ObjectType;
import ca.spottedleaf.dataconverter.types.MapType;
import ca.spottedleaf.dataconverter.util.Long2ObjectArraySortedMap;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.slf4j.Logger;
import java.util.HashMap;
import java.util.Map;

public final class V704 {

    private static final Logger LOGGER = LogUtils.getLogger();

    private static final int VERSION = MCVersions.V1_10_2 + 192;

    public static final Map<String, String> ITEM_ID_TO_TILE_ENTITY_ID = new HashMap<>() {
        @Override
        public String put(final String key, final String value) {
            if (this.containsKey(key)) {
                LOGGER.error("Duplicate item id to tile key: " + key);
                throw new RuntimeException(); // only devs should see the consequence of this... at least start up the damn thing...
            }
            return super.put(key, value);
        }
    };
    static {
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:furnace", "minecraft:furnace");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:lit_furnace", "minecraft:furnace");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:chest", "minecraft:chest");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:trapped_chest", "minecraft:chest");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:ender_chest", "minecraft:ender_chest");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:jukebox", "minecraft:jukebox");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:dispenser", "minecraft:dispenser");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:dropper", "minecraft:dropper");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:mob_spawner", "minecraft:mob_spawner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:spawner", "minecraft:mob_spawner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:noteblock", "minecraft:noteblock");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:brewing_stand", "minecraft:brewing_stand");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:enhanting_table", "minecraft:enchanting_table");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:command_block", "minecraft:command_block");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:beacon", "minecraft:beacon");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:skull", "minecraft:skull");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:daylight_detector", "minecraft:daylight_detector");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:hopper", "minecraft:hopper");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:flower_pot", "minecraft:flower_pot");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:repeating_command_block", "minecraft:command_block");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:chain_command_block", "minecraft:command_block");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:white_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:orange_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:magenta_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:light_blue_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:yellow_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:lime_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:pink_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:gray_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:silver_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:cyan_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:purple_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:blue_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:brown_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:green_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:red_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:black_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:light_gray_shulker_box", "minecraft:shulker_box");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:white_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:orange_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:magenta_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:light_blue_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:yellow_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:lime_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:pink_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:gray_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:silver_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:cyan_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:purple_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:blue_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:brown_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:green_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:red_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:black_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:standing_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:wall_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:piston_head", "minecraft:piston");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:daylight_detector_inverted", "minecraft:daylight_detector");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:unpowered_comparator", "minecraft:comparator");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:powered_comparator", "minecraft:comparator");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:wall_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:standing_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:structure_block", "minecraft:structure_block");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:end_portal", "minecraft:end_portal");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:end_gateway", "minecraft:end_gateway");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:shield", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:white_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:orange_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:magenta_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:light_blue_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:yellow_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:lime_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:pink_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:gray_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:silver_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:cyan_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:purple_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:blue_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:brown_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:green_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:red_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:black_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:oak_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:spruce_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:birch_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:jungle_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:acacia_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:dark_oak_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:crimson_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:warped_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:skeleton_skull", "minecraft:skull");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:wither_skeleton_skull", "minecraft:skull");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:zombie_head", "minecraft:skull");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:player_head", "minecraft:skull");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:creeper_head", "minecraft:skull");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:dragon_head", "minecraft:skull");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:barrel", "minecraft:barrel");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:conduit", "minecraft:conduit");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:smoker", "minecraft:smoker");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:blast_furnace", "minecraft:blast_furnace");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:lectern", "minecraft:lectern");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:bell", "minecraft:bell");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:jigsaw", "minecraft:jigsaw");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:campfire", "minecraft:campfire");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:bee_nest", "minecraft:beehive");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:beehive", "minecraft:beehive");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:sculk_sensor", "minecraft:sculk_sensor");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:decorated_pot", "minecraft:decorated_pot");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:crafter", "minecraft:crafter");

        // These are missing from Vanilla up to 1.20.5
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:enchanting_table", "minecraft:enchanting_table");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:comparator", "minecraft:comparator");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:light_gray_bed", "minecraft:bed");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:light_gray_banner", "minecraft:banner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:soul_campfire", "minecraft:campfire");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:sculk_catalyst", "minecraft:sculk_catalyst");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:mangrove_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:sculk_shrieker", "minecraft:sculk_shrieker");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:chiseled_bookshelf", "minecraft:chiseled_bookshelf");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:bamboo_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:oak_hanging_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:spruce_hanging_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:birch_hanging_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:jungle_hanging_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:acacia_hanging_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:dark_oak_hanging_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:mangrove_hanging_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:bamboo_hanging_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:crimson_hanging_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:warped_hanging_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:piglin_head", "minecraft:skull");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:suspicious_sand", "minecraft:brushable_block"); // note: this was renamed in the past, see special case in the itemstack walker
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:cherry_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:cherry_hanging_sign", "minecraft:sign");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:suspicious_gravel", "minecraft:brushable_block");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:calibrated_sculk_sensor", "minecraft:calibrated_sculk_sensor");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:trial_spawner", "minecraft:trial_spawner");
        ITEM_ID_TO_TILE_ENTITY_ID.put("minecraft:vault", "minecraft:vault");
    }

    // This class is responsible for also integrity checking the item id to tile id map here, we just use the item registry to figure it out
    // No longer need to do this, as items now use components in 1.20.5
    /*static {
        for (final Item item : BuiltInRegistries.ITEM) {
            if (!(item instanceof BlockItem)) {
                continue;
            }

            if (!(((BlockItem)item).getBlock() instanceof EntityBlock entityBlock)) {
                continue;
            }

            String possibleId;
            try {
                final BlockEntity entity = entityBlock.newBlockEntity(new BlockPos(0, 0, 0), ((Block)entityBlock).defaultBlockState());
                if (entity != null) {
                    possibleId = BlockEntityType.getKey(entity.getType()).toString();
                } else {
                    possibleId = null;
                }
            } catch (final Throwable th) {
                possibleId = null;
            }

            final String itemName = BuiltInRegistries.ITEM.getKey(item).toString();
            final String mappedTo = ITEM_ID_TO_TILE_ENTITY_ID.get(itemName);
            if (mappedTo == null) {
                LOGGER.error("Item id " + itemName + " does not contain tile mapping! (V704)");
            } else if (possibleId != null && !mappedTo.equals(possibleId)) {
                final boolean chestCase = mappedTo.equals("minecraft:chest") && possibleId.equals("minecraft:trapped_chest");
                final boolean signCase = mappedTo.equals("minecraft:sign") && possibleId.equals("minecraft:hanging_sign");
                // save data is identical for the chest and sign case, so we don't care
                // it's also important to note that there is no versioning for this map, so it is possible
                // that mapping them correctly could cause issues converting old data
                if (!chestCase && !signCase) {
                    LOGGER.error("Item id " + itemName + " is mapped to the wrong tile entity! Mapped to: " + mappedTo + ", expected: " + possibleId);
                }
            }
        }
    }*/

    private static Long2ObjectArraySortedMap<String> makeSingle(final long k1, final String v1) {
        final Long2ObjectArraySortedMap<String> ret = new Long2ObjectArraySortedMap<>();

        ret.put(k1, v1);

        return ret;
    }

    private static Long2ObjectArraySortedMap<String> makeDouble(final long k1, final String v1,
                                                                final long k2, final String v2) {
        final Long2ObjectArraySortedMap<String> ret = new Long2ObjectArraySortedMap<>();

        ret.put(k1, v1);
        ret.put(k2, v2);

        return ret;
    }

    private static final Map<String, Long2ObjectArraySortedMap<String>> ITEM_ID_TO_ENTITY_ID = new HashMap<>();
    static {
        ITEM_ID_TO_ENTITY_ID.put("minecraft:armor_stand", makeDouble(V99.VERSION, "ArmorStand", V705.VERSION, "minecraft:armor_stand"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:painting", makeDouble(V99.VERSION, "Painting", V705.VERSION, "minecraft:painting"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:boat", makeDouble(V99.VERSION, "Boat", V705.VERSION, "minecraft:boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:oak_boat", makeSingle(V705.VERSION, "minecraft:boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:oak_chest_boat", makeSingle(V705.VERSION, "minecraft:chest_boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:spruce_boat",makeSingle(V705.VERSION, "minecraft:boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:spruce_chest_boat", makeSingle(V705.VERSION, "minecraft:chest_boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:birch_boat", makeSingle(V705.VERSION, "minecraft:boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:birch_chest_boat", makeSingle(V705.VERSION, "minecraft:chest_boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:jungle_boat", makeSingle(V705.VERSION, "minecraft:boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:jungle_chest_boat", makeSingle(V705.VERSION, "minecraft:chest_boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:acacia_boat", makeSingle(V705.VERSION, "minecraft:boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:acacia_chest_boat", makeSingle(V705.VERSION, "minecraft:chest_boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:cherry_boat", makeSingle(V705.VERSION, "minecraft:boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:cherry_chest_boat", makeSingle(V705.VERSION, "minecraft:chest_boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:dark_oak_boat", makeSingle(V705.VERSION, "minecraft:boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:dark_oak_chest_boat", makeSingle(V705.VERSION, "minecraft:chest_boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:mangrove_boat", makeSingle(V705.VERSION, "minecraft:boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:mangrove_chest_boat", makeSingle(V705.VERSION, "minecraft:chest_boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:bamboo_raft", makeSingle(V705.VERSION, "minecraft:boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:bamboo_chest_raft", makeSingle(V705.VERSION, "minecraft:chest_boat"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:minecart", makeDouble(V99.VERSION, "MinecartRideable", V705.VERSION, "minecraft:minecart"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:chest_minecart", makeDouble(V99.VERSION, "MinecartChest", V705.VERSION, "minecraft:chest_minecart"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:furnace_minecart", makeDouble(V99.VERSION, "MinecartFurnace", V705.VERSION, "minecraft:furnace_minecart"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:tnt_minecart", makeDouble(V99.VERSION, "MinecartTNT", V705.VERSION, "minecraft:tnt_minecart"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:hopper_minecart", makeDouble(V99.VERSION, "MinecartHopper", V705.VERSION, "minecraft:hopper_minecart"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:item_frame", makeDouble(V99.VERSION, "ItemFrame", V705.VERSION, "minecraft:item_frame"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:glow_item_frame", makeSingle(V705.VERSION, "minecraft:glow_item_frame"));

        // Mojang missed these
        ITEM_ID_TO_ENTITY_ID.put("minecraft:pufferfish_bucket", makeSingle(V705.VERSION, "minecraft:pufferfish"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:salmon_bucket", makeSingle(V705.VERSION, "minecraft:salmon"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:cod_bucket", makeSingle(V705.VERSION, "minecraft:cod"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:tropical_fish_bucket", makeSingle(V705.VERSION, "minecraft:tropical_fish"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:axolotl_bucket", makeSingle(V705.VERSION, "minecraft:axolotl"));
        ITEM_ID_TO_ENTITY_ID.put("minecraft:tadpole_bucket", makeSingle(V705.VERSION, "minecraft:tadpole"));
    }

    private static final Map<String, String> TILE_ID_UPDATE = new HashMap<>();
    static {
        TILE_ID_UPDATE.put("Airportal", "minecraft:end_portal");
        TILE_ID_UPDATE.put("Banner", "minecraft:banner");
        TILE_ID_UPDATE.put("Beacon", "minecraft:beacon");
        TILE_ID_UPDATE.put("Cauldron", "minecraft:brewing_stand");
        TILE_ID_UPDATE.put("Chest", "minecraft:chest");
        TILE_ID_UPDATE.put("Comparator", "minecraft:comparator");
        TILE_ID_UPDATE.put("Control", "minecraft:command_block");
        TILE_ID_UPDATE.put("DLDetector", "minecraft:daylight_detector");
        TILE_ID_UPDATE.put("Dropper", "minecraft:dropper");
        TILE_ID_UPDATE.put("EnchantTable", "minecraft:enchanting_table");
        TILE_ID_UPDATE.put("EndGateway", "minecraft:end_gateway");
        TILE_ID_UPDATE.put("EnderChest", "minecraft:ender_chest");
        TILE_ID_UPDATE.put("FlowerPot", "minecraft:flower_pot");
        TILE_ID_UPDATE.put("Furnace", "minecraft:furnace");
        TILE_ID_UPDATE.put("Hopper", "minecraft:hopper");
        TILE_ID_UPDATE.put("MobSpawner", "minecraft:mob_spawner");
        TILE_ID_UPDATE.put("Music", "minecraft:noteblock");
        TILE_ID_UPDATE.put("Piston", "minecraft:piston");
        TILE_ID_UPDATE.put("RecordPlayer", "minecraft:jukebox");
        TILE_ID_UPDATE.put("Sign", "minecraft:sign");
        TILE_ID_UPDATE.put("Skull", "minecraft:skull");
        TILE_ID_UPDATE.put("Structure", "minecraft:structure_block");
        TILE_ID_UPDATE.put("Trap", "minecraft:dispenser");
    }

    private static void registerInventory(final String id) {
        MCTypeRegistry.TILE_ENTITY.addWalker(VERSION, id, new DataWalkerItemLists("Items"));
    }

    public static void register() {
        MCTypeRegistry.TILE_ENTITY.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final String id = data.getString("id");
                if (id == null) {
                    return null;
                }

                data.setString("id", TILE_ID_UPDATE.getOrDefault(id, id));
                return null;
            }
        });


        MCTypeRegistry.TILE_ENTITY.addStructureWalker(VERSION, (final MapType<String> data, final long fromVersion, final long toVersion) -> {
            WalkerUtils.convert(MCTypeRegistry.DATA_COMPONENTS, data, "components", fromVersion, toVersion);
            return null;
        });
        registerInventory( "minecraft:furnace");
        registerInventory( "minecraft:chest");
        MCTypeRegistry.TILE_ENTITY.addWalker(VERSION, "minecraft:jukebox", new DataWalkerItems("RecordItem"));
        registerInventory("minecraft:dispenser");
        registerInventory("minecraft:dropper");
        MCTypeRegistry.TILE_ENTITY.addWalker(VERSION, "minecraft:mob_spawner", (final MapType<String> data, final long fromVersion, final long toVersion) -> {
            MCTypeRegistry.UNTAGGED_SPAWNER.convert(data, fromVersion, toVersion);
            return null;
        });
        registerInventory("minecraft:brewing_stand");
        registerInventory("minecraft:hopper");
        MCTypeRegistry.TILE_ENTITY.addWalker(VERSION, "minecraft:flower_pot", new DataWalkerItemNames("Item"));
        MCTypeRegistry.TILE_ENTITY.addWalker(
                VERSION, "minecraft:command_block",
                new DataWalkerTypePaths<>(MCTypeRegistry.DATACONVERTER_CUSTOM_TYPE_COMMAND, "Command")
        );

        MCTypeRegistry.ITEM_STACK.addStructureWalker(VERSION, (final MapType<String> data, final long fromVersion, final long toVersion) -> {
            WalkerUtils.convert(MCTypeRegistry.ITEM_NAME, data, "id", fromVersion, toVersion);

            final MapType<String> tag = data.getMap("tag");
            if (tag == null) {
                return null;
            }

            // only things here are in tag, if changed update if above

            WalkerUtils.convertList(MCTypeRegistry.ITEM_STACK, tag, "Items", fromVersion, toVersion);
            WalkerUtils.convertList(MCTypeRegistry.ITEM_STACK, tag, "ChargedProjectiles", fromVersion, toVersion);

            MapType<String> entityTag = tag.getMap("EntityTag");
            if (entityTag != null) {
                final String itemId = data.getString("id");
                final String entityId;
                if (itemId != null && itemId.contains("_spawn_egg")) {
                    // V1451 changes spawn eggs to have the sub entity id be a part of the item id, but of course Mojang never
                    // bothered to write in logic to set the sub entity id, so we have to.
                    // format is ALWAYS <namespace>:<id>_spawn_egg post flattening
                    entityId = itemId.substring(0, itemId.indexOf("_spawn_egg"));
                } else {
                    final Long2ObjectArraySortedMap<String> mappingByVersion = ITEM_ID_TO_ENTITY_ID.get(itemId);
                    final String mapped = mappingByVersion == null ? null : mappingByVersion.getFloor(fromVersion);
                    entityId = mapped == null ? entityTag.getString("id") : mapped;
                }

                if (entityId == null) {
                    if (!"minecraft:air".equals(itemId)) {
                        LOGGER.warn("Unable to resolve Entity for ItemStack (V704): " + itemId);
                    }
                } else {
                    if (!entityTag.hasKey("id", ObjectType.STRING)) {
                        entityTag.setString("id", entityId);
                    }
                }

                final MapType<String> replace = MCTypeRegistry.ENTITY.convert(entityTag, fromVersion, toVersion);

                if (replace != null) {
                    entityTag = replace;
                    tag.setMap("EntityTag", entityTag);
                }
            }

            MapType<String> blockEntityTag = tag.getMap("BlockEntityTag");
            if (blockEntityTag != null) {
                final String itemId = data.getString("id");
                final String entityId;
                if ("minecraft:suspicious_sand".equals(itemId) && fromVersion < V3438.VERSION) {
                    // renamed after this version, and since the id is a mapping to just string we need to special case this
                    entityId = "minecraft:suspicious_sand";
                } else {
                    entityId = ITEM_ID_TO_TILE_ENTITY_ID.get(itemId);
                }

                if (entityId == null) {
                    if (!"minecraft:air".equals(itemId)) {
                        LOGGER.warn("Unable to resolve BlockEntity for ItemStack (V704): " + itemId);
                    }
                } else {
                    if (!blockEntityTag.hasKey("id", ObjectType.STRING)) {
                        blockEntityTag.setString("id", entityId);
                    }
                }
                final MapType<String> replace = MCTypeRegistry.TILE_ENTITY.convert(blockEntityTag, fromVersion, toVersion);
                if (replace != null) {
                    blockEntityTag = replace;
                    tag.setMap("BlockEntityTag", blockEntityTag);
                }
            }

            WalkerUtils.convertList(MCTypeRegistry.BLOCK_NAME, tag, "CanDestroy", fromVersion, toVersion);
            WalkerUtils.convertList(MCTypeRegistry.BLOCK_NAME, tag, "CanPlaceOn", fromVersion, toVersion);

            return null;
        });

        // Enforce namespace for ids
        MCTypeRegistry.TILE_ENTITY.addStructureHook(VERSION, new DataHookEnforceNamespacedID());
    }

    private V704() {}
}
