package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.ListType;
import ca.spottedleaf.dataconverter.common.types.MapType;
import ca.spottedleaf.dataconverter.common.types.ObjectType;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class V1624 {

    private static final Logger LOGGER = LogManager.getLogger();

    protected static final int VERSION = MCVersions.V18W32A + 1;

    private V1624() {}

    public static void register() {
        MCTypeRegistry.CHUNK.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final MapType<String> level = data.getMap("Level");

                if (level == null) {
                    return null;
                }

                final ListType sections = level.getList("Sections", ObjectType.MAP);
                if (sections == null) {
                    return null;
                }

                final IntOpenHashSet positionsToLook = new IntOpenHashSet();

                for (int i = 0, len = sections.size(); i < len; ++i) {
                    final TrappedChestSection section = new TrappedChestSection(sections.getMap(i));
                    if (section.isSkippable()) {
                        continue;
                    }

                    for (int index = 0; index < 4096; ++index) {
                        if (section.isTrappedChest(section.getBlock(index))) {
                            positionsToLook.add(section.getSectionY() << 12 | index);
                        }
                    }
                }

                final int chunkX = level.getInt("xPos");
                final int chunkZ = level.getInt("zPos");

                final ListType tileEntities = level.getList("TileEntities", ObjectType.MAP);

                if (tileEntities != null) {
                    for (int i = 0, len = tileEntities.size(); i < len; ++i) {
                        final MapType<String> tile = tileEntities.getMap(i);

                        final int x = tile.getInt("x");
                        final int y = tile.getInt("y");
                        final int z = tile.getInt("z");

                        final int index = V1496.getIndex(x - (chunkX << 4), y, z - (chunkZ << 4));
                        if (!positionsToLook.contains(index)) {
                            continue;
                        }

                        final String id = tile.getString("id");
                        if (!"minecraft:chest".equals(id)) {
                            LOGGER.warn("Block Entity ({},{},{}) was expected to be a chest", x, y, z);
                        }

                        tile.setString("id", "minecraft:trapped_chest");
                    }
                }

                return null;
            }
        });
    }

    public static final class TrappedChestSection extends V1496.Section {

        private IntOpenHashSet chestIds;

        public TrappedChestSection(final MapType<String> section) {
            super(section);
        }

        @Override
        protected boolean initSkippable() {
            this.chestIds = new IntOpenHashSet();

            for (int i = 0; i < this.palette.size(); ++i) {
                final MapType<String> blockState = this.palette.getMap(i);
                final String name = blockState.getString("Name");
                if ("minecraft:trapped_chest".equals(name)) {
                    this.chestIds.add(i);
                }
            }

            return this.chestIds.isEmpty();
        }

        public boolean isTrappedChest(final int id) {
            return this.chestIds.contains(id);
        }
    }
}
