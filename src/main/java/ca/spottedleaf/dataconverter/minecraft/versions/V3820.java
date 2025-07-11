package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.itemstack.ConverterItemStackToDataComponents;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.walkers.generic.DataWalkerTypePaths;
import ca.spottedleaf.dataconverter.types.MapType;

public final class V3820 {

    private static final int VERSION = MCVersions.V24W09A + 1;

    public static void register() {
        MCTypeRegistry.TILE_ENTITY.addConverterForId("minecraft:skull", new DataConverter<>(VERSION) {
            @Override
            public MapType convert(final MapType data, final long sourceVersion, final long toVersion) {
                final Object skullOwner = data.getGeneric("SkullOwner");
                final Object extraType = data.getGeneric("ExtraType");

                if (skullOwner == null && extraType == null) {
                    return null;
                }

                data.remove("SkullOwner");
                data.remove("ExtraType");

                data.setMap(
                        "profile",
                        ConverterItemStackToDataComponents.convertProfile(
                                skullOwner == null ? extraType : skullOwner, data.getTypeUtil()
                        )
                );

                return null;
            }
        });
        // I don't see why this converter is necessary, V3818 should have converted correctly...
        MCTypeRegistry.ITEM_STACK.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType convert(final MapType data, final long sourceVersion, final long toVersion) {
                final MapType components = data.getMap("components");

                if (components == null) {
                    return null;
                }

                final MapType oldTarget = components.getMap("minecraft:lodestone_target");
                if (oldTarget == null) {
                    return null;
                }

                components.remove("minecraft:lodestone_target");
                components.setMap("minecraft:lodestone_tracker", oldTarget);

                final Object pos = oldTarget.getGeneric("pos");
                final Object dim = oldTarget.getGeneric("dimension");

                if (pos == null || dim == null) {
                    return null;
                }

                oldTarget.remove("pos");
                oldTarget.remove("dimension");

                final MapType target = oldTarget.getTypeUtil().createEmptyMap();
                oldTarget.setMap("target", target);

                target.setGeneric("pos", pos);
                target.setGeneric("dimension", dim);

                return null;
            }
        });

        // Moved from V99 to here, as 21w10a is when custom_name was added
        MCTypeRegistry.TILE_ENTITY.addWalker(VERSION, "minecraft:skull", new DataWalkerTypePaths<>(MCTypeRegistry.TEXT_COMPONENT, "custom_name"));
    }

    private V3820() {}
}
