package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.minecraft.walkers.generic.WalkerUtils;
import ca.spottedleaf.dataconverter.common.types.ListType;
import ca.spottedleaf.dataconverter.common.types.MapType;
import ca.spottedleaf.dataconverter.common.types.ObjectType;

public final class V1929 {

    protected static final int VERSION = MCVersions.V19W04B + 2;

    private V1929() {}

    public static void register() {
        MCTypeRegistry.ENTITY.addWalker(VERSION, "minecraft:wandering_trader", (final MapType<String> data, final long fromVersion, final long toVersion) -> {
            WalkerUtils.convertList(MCTypeRegistry.ITEM_STACK, data, "Inventory", fromVersion, toVersion);

            final MapType<String> offers = data.getMap("Offers");
            if (offers != null) {
                final ListType recipes = offers.getList("Recipes", ObjectType.MAP);
                if (recipes != null) {
                    for (int i = 0, len = recipes.size(); i < len; ++i) {
                        final MapType<String> recipe = recipes.getMap(i);
                        WalkerUtils.convert(MCTypeRegistry.ITEM_STACK, recipe, "buy", fromVersion, toVersion);
                        WalkerUtils.convert(MCTypeRegistry.ITEM_STACK, recipe, "buyB", fromVersion, toVersion);
                        WalkerUtils.convert(MCTypeRegistry.ITEM_STACK, recipe, "sell", fromVersion, toVersion);
                    }
                }
            }

            WalkerUtils.convertList(MCTypeRegistry.ITEM_STACK, data, "ArmorItems", fromVersion, toVersion);
            WalkerUtils.convertList(MCTypeRegistry.ITEM_STACK, data, "HandItems", fromVersion, toVersion);
        });
        MCTypeRegistry.ENTITY.addWalker(VERSION, "minecraft:trader_llama", (final MapType<String> data, final long fromVersion, final long toVersion) -> {
            WalkerUtils.convert(MCTypeRegistry.ITEM_STACK, data, "SaddleItem", fromVersion, toVersion);
            WalkerUtils.convert(MCTypeRegistry.ITEM_STACK, data, "DecorItem", fromVersion, toVersion);

            WalkerUtils.convertList(MCTypeRegistry.ITEM_STACK, data, "Items", fromVersion, toVersion);
            WalkerUtils.convertList(MCTypeRegistry.ITEM_STACK, data, "ArmorItems", fromVersion, toVersion);
            WalkerUtils.convertList(MCTypeRegistry.ITEM_STACK, data, "HandItems", fromVersion, toVersion);
        });
    }
}
