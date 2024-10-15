package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.types.MapType;
import ca.spottedleaf.dataconverter.types.TypeUtil;
import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;

public final class V4068 {
    public static final Escaper ESCAPER = Escapers.builder().addEscape('"', "\\\"").addEscape('\\', "\\\\").build();

    private static final int VERSION = MCVersions.V24W38A + 2;

    private static void convertLock(final MapType<String> root, final String srcPath, final String dstPath) {
        if (root == null) {
            return;
        }

        final Object lockGeneric = root.getGeneric(srcPath);
        if (lockGeneric == null) {
            return;
        }

        final TypeUtil typeUtil = root.getTypeUtil();

        final MapType<String> newLock = typeUtil.createEmptyMap();
        root.remove(srcPath);
        root.setMap(dstPath, newLock);

        if (lockGeneric instanceof String lock) {
            final MapType<String> lockComponents = typeUtil.createEmptyMap();
            newLock.setMap("components", lockComponents);

            lockComponents.setString("minecraft:custom_name", ESCAPER.escape(lock));
        }
    }

    public static void register() {
        MCTypeRegistry.ITEM_STACK.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final MapType<String> components = data.getMap("components");
                if (components == null) {
                    return null;
                }

                convertLock(components, "minecraft:lock", "minecraft:lock");

                return null;
            }
        });
        MCTypeRegistry.TILE_ENTITY.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                convertLock(data, "Lock", "lock");
                return null;
            }
        });
    }

    private V4068() {}
}
