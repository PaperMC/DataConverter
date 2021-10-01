package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.converters.helpers.RenameHelper;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.walkers.generic.WalkerUtils;
import ca.spottedleaf.dataconverter.types.ListType;
import ca.spottedleaf.dataconverter.types.MapType;
import ca.spottedleaf.dataconverter.types.ObjectType;
import ca.spottedleaf.dataconverter.types.Types;
import it.unimi.dsi.fastutil.ints.Int2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class V2832 {

    protected static final Logger LOGGER = LogManager.getLogger();

    protected static final int VERSION = MCVersions.V1_17_1 + 102;

    private static final String[] BIOMES_BY_ID = new String[256]; // rip datapacks
    static {
        BIOMES_BY_ID[0] = "minecraft:ocean";
        BIOMES_BY_ID[1] = "minecraft:plains";
        BIOMES_BY_ID[2] = "minecraft:desert";
        BIOMES_BY_ID[3] = "minecraft:mountains";
        BIOMES_BY_ID[4] = "minecraft:forest";
        BIOMES_BY_ID[5] = "minecraft:taiga";
        BIOMES_BY_ID[6] = "minecraft:swamp";
        BIOMES_BY_ID[7] = "minecraft:river";
        BIOMES_BY_ID[8] = "minecraft:nether_wastes";
        BIOMES_BY_ID[9] = "minecraft:the_end";
        BIOMES_BY_ID[10] = "minecraft:frozen_ocean";
        BIOMES_BY_ID[11] = "minecraft:frozen_river";
        BIOMES_BY_ID[12] = "minecraft:snowy_tundra";
        BIOMES_BY_ID[13] = "minecraft:snowy_mountains";
        BIOMES_BY_ID[14] = "minecraft:mushroom_fields";
        BIOMES_BY_ID[15] = "minecraft:mushroom_field_shore";
        BIOMES_BY_ID[16] = "minecraft:beach";
        BIOMES_BY_ID[17] = "minecraft:desert_hills";
        BIOMES_BY_ID[18] = "minecraft:wooded_hills";
        BIOMES_BY_ID[19] = "minecraft:taiga_hills";
        BIOMES_BY_ID[20] = "minecraft:mountain_edge";
        BIOMES_BY_ID[21] = "minecraft:jungle";
        BIOMES_BY_ID[22] = "minecraft:jungle_hills";
        BIOMES_BY_ID[23] = "minecraft:jungle_edge";
        BIOMES_BY_ID[24] = "minecraft:deep_ocean";
        BIOMES_BY_ID[25] = "minecraft:stone_shore";
        BIOMES_BY_ID[26] = "minecraft:snowy_beach";
        BIOMES_BY_ID[27] = "minecraft:birch_forest";
        BIOMES_BY_ID[28] = "minecraft:birch_forest_hills";
        BIOMES_BY_ID[29] = "minecraft:dark_forest";
        BIOMES_BY_ID[30] = "minecraft:snowy_taiga";
        BIOMES_BY_ID[31] = "minecraft:snowy_taiga_hills";
        BIOMES_BY_ID[32] = "minecraft:giant_tree_taiga";
        BIOMES_BY_ID[33] = "minecraft:giant_tree_taiga_hills";
        BIOMES_BY_ID[34] = "minecraft:wooded_mountains";
        BIOMES_BY_ID[35] = "minecraft:savanna";
        BIOMES_BY_ID[36] = "minecraft:savanna_plateau";
        BIOMES_BY_ID[37] = "minecraft:badlands";
        BIOMES_BY_ID[38] = "minecraft:wooded_badlands_plateau";
        BIOMES_BY_ID[39] = "minecraft:badlands_plateau";
        BIOMES_BY_ID[40] = "minecraft:small_end_islands";
        BIOMES_BY_ID[41] = "minecraft:end_midlands";
        BIOMES_BY_ID[42] = "minecraft:end_highlands";
        BIOMES_BY_ID[43] = "minecraft:end_barrens";
        BIOMES_BY_ID[44] = "minecraft:warm_ocean";
        BIOMES_BY_ID[45] = "minecraft:lukewarm_ocean";
        BIOMES_BY_ID[46] = "minecraft:cold_ocean";
        BIOMES_BY_ID[47] = "minecraft:deep_warm_ocean";
        BIOMES_BY_ID[48] = "minecraft:deep_lukewarm_ocean";
        BIOMES_BY_ID[49] = "minecraft:deep_cold_ocean";
        BIOMES_BY_ID[50] = "minecraft:deep_frozen_ocean";
        BIOMES_BY_ID[127] = "minecraft:the_void";
        BIOMES_BY_ID[129] = "minecraft:sunflower_plains";
        BIOMES_BY_ID[130] = "minecraft:desert_lakes";
        BIOMES_BY_ID[131] = "minecraft:gravelly_mountains";
        BIOMES_BY_ID[132] = "minecraft:flower_forest";
        BIOMES_BY_ID[133] = "minecraft:taiga_mountains";
        BIOMES_BY_ID[134] = "minecraft:swamp_hills";
        BIOMES_BY_ID[140] = "minecraft:ice_spikes";
        BIOMES_BY_ID[149] = "minecraft:modified_jungle";
        BIOMES_BY_ID[151] = "minecraft:modified_jungle_edge";
        BIOMES_BY_ID[155] = "minecraft:tall_birch_forest";
        BIOMES_BY_ID[156] = "minecraft:tall_birch_hills";
        BIOMES_BY_ID[157] = "minecraft:dark_forest_hills";
        BIOMES_BY_ID[158] = "minecraft:snowy_taiga_mountains";
        BIOMES_BY_ID[160] = "minecraft:giant_spruce_taiga";
        BIOMES_BY_ID[161] = "minecraft:giant_spruce_taiga_hills";
        BIOMES_BY_ID[162] = "minecraft:modified_gravelly_mountains";
        BIOMES_BY_ID[163] = "minecraft:shattered_savanna";
        BIOMES_BY_ID[164] = "minecraft:shattered_savanna_plateau";
        BIOMES_BY_ID[165] = "minecraft:eroded_badlands";
        BIOMES_BY_ID[166] = "minecraft:modified_wooded_badlands_plateau";
        BIOMES_BY_ID[167] = "minecraft:modified_badlands_plateau";
        BIOMES_BY_ID[168] = "minecraft:bamboo_jungle";
        BIOMES_BY_ID[169] = "minecraft:bamboo_jungle_hills";
        BIOMES_BY_ID[170] = "minecraft:soul_sand_valley";
        BIOMES_BY_ID[171] = "minecraft:crimson_forest";
        BIOMES_BY_ID[172] = "minecraft:warped_forest";
        BIOMES_BY_ID[173] = "minecraft:basalt_deltas";
        BIOMES_BY_ID[174] = "minecraft:dripstone_caves";
        BIOMES_BY_ID[175] = "minecraft:lush_caves";
        BIOMES_BY_ID[177] = "minecraft:meadow";
        BIOMES_BY_ID[178] = "minecraft:grove";
        BIOMES_BY_ID[179] = "minecraft:snowy_slopes";
        BIOMES_BY_ID[180] = "minecraft:snowcapped_peaks";
        BIOMES_BY_ID[181] = "minecraft:lofty_peaks";
        BIOMES_BY_ID[182] = "minecraft:stony_peaks";
    }

    private static final String[] HEIGHTMAP_TYPES = new String[] {
            "WORLD_SURFACE_WG",
            "WORLD_SURFACE",
            "WORLD_SURFACE_IGNORE_SNOW",
            "OCEAN_FLOOR_WG",
            "OCEAN_FLOOR",
            "MOTION_BLOCKING",
            "MOTION_BLOCKING_NO_LEAVES"
    };



    public static void register() {
        // See V2551 for the layout of world gen settings
        MCTypeRegistry.WORLD_GEN_SETTINGS.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                // converters were added to older versions note whether the world has increased height already or not
                final boolean noHeightFlag = !data.hasKey("has_increased_height_already");
                final boolean hasIncreasedHeight = data.getBoolean("has_increased_height_already", true);
                data.remove("has_increased_height_already");

                final MapType<String> dimensions = data.getMap("dimensions");
                if (dimensions == null) {
                    // wat
                    return null;
                }

                // only care about overworld
                final MapType<String> overworld = dimensions.getMap("minecraft:overworld");
                if (overworld == null) {
                    // wat
                    return null;
                }

                final MapType<String> generator = overworld.getMap("generator");
                if (generator == null) {
                    // wat
                    return null;
                }

                final String type = generator.getString("type", "");
                switch (type) {
                    case "minecraft:noise": {
                        final MapType<String> biomeSource = generator.getMap("biome_source");
                        final String sourceType = biomeSource.getString("type");

                        boolean largeBiomes = false;

                        if ("minecraft:vanilla_layered".equals(sourceType) || (noHeightFlag && "minecraft:multi_noise".equals(sourceType))) {
                            largeBiomes = biomeSource.getBoolean("large_biomes");

                            final MapType<String> newBiomeSource = Types.NBT.createEmptyMap();
                            generator.setMap("biome_source", newBiomeSource);

                            newBiomeSource.setString("preset", "minecraft:overworld");
                            newBiomeSource.setString("type", "minecraft:multi_noise");
                        }

                        if (largeBiomes) {
                            if ("minecraft:overworld".equals(generator.getString("settings"))) {
                                generator.setString("settings", "minecraft:large_biomes");
                            }
                        }

                        break;
                    }
                    case "minecraft:flat": {
                        if (!hasIncreasedHeight) {
                            final MapType<String> settings = generator.getMap("settings");
                            if (settings == null) {
                                break;
                            }

                            updateLayers(settings.getList("layers", ObjectType.MAP));
                        }
                        break;
                    }
                    default:
                        // do nothing
                        break;
                }

                return null;
            }
        });


        // It looks like DFU will only support worlds in the old height format or the new one, any custom one isn't supported
        // and by not supported I mean it will just treat it as the old format... maybe at least throw in that case?
        // TODO add support for old custom worldheights?
        MCTypeRegistry.CHUNK.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final MapType<String> level = data.getMap("Level");

                if (level == null) {
                    return null;
                }

                final String dimension = level.getString("__dimension"); // Passed through by ChunkStorage
                final boolean isOverworld = "minecraft:overworld".equals(dimension);
                final int minSection = isOverworld ? -4 : 0;
                final MutableBoolean isAlreadyExtended = new MutableBoolean();

                final MapType<String>[] newBiomes = createBiomeSections(level, isOverworld, minSection, isAlreadyExtended);
                final MapType<String> wrappedEmptyBlockPalette = getEmptyBlockPalette();

                final ListType sections = level.getList("Sections", ObjectType.MAP);

                // must update sections for two things:
                // 1. the biomes are now stored per section, so we must insert the biomes palette into each section (and create them if they don't exist)
                // 2. each section must now have block states (or at least DFU is ensuring they do, but current code does not require)
                if (sections != null) {
                    final IntOpenHashSet existingSections = new IntOpenHashSet();

                    for (int i = 0, len = sections.size(); i < len; ++i) {
                        final MapType<String> section = sections.getMap(i);

                        final int y = section.getInt("Y");
                        final int sectionIndex = y - minSection;

                        existingSections.add(y);

                        // add in relevant biome section
                        if (sectionIndex >= 0 && sectionIndex < newBiomes.length) {
                            // exclude out of bounds sections (i.e the light sections above and below the world)
                            section.setMap("biomes", newBiomes[sectionIndex]);
                        }

                        // update palette
                        final ListType palette = section.getList("Palette", ObjectType.MAP);
                        final long[] blockStates = section.getLongs("BlockStates");

                        section.remove("Palette");
                        section.remove("BlockStates");

                        if (palette != null && blockStates != null) {
                            // only if both exist, same as DFU, same as legacy chunk loading code
                            section.setMap("block_states", wrapPaletteOptimised(palette, blockStates));
                        } else {
                            section.setMap("block_states", wrappedEmptyBlockPalette.copy()); // must write a palette now, copy so that later edits do not edit them all
                        }
                    }

                    // all existing sections updated, now we must create new sections just for the biomes migration
                    for (int sectionIndex = 0; sectionIndex < newBiomes.length; ++sectionIndex) {
                        final int sectionY = sectionIndex + minSection;
                        if (!existingSections.add(sectionY)) {
                            // exists already
                            continue;
                        }

                        final MapType<String> newSection = Types.NBT.createEmptyMap();
                        sections.addMap(newSection);

                        newSection.setByte("Y", (byte)sectionY);
                        // must write a palette now, copy so that later edits do not edit them all
                        newSection.setMap("block_states", wrappedEmptyBlockPalette.copy());

                        newSection.setGeneric("biomes", newBiomes[sectionIndex]);
                    }
                }

                // done with sections, update the rest of the chunk
                updateChunkData(level, isOverworld, isAlreadyExtended.getValue());

                return null;
            }
        });

        MCTypeRegistry.WORLD_GEN_SETTINGS.addStructureWalker(VERSION, (final MapType<String> data, final long fromVersion, final long toVersion) -> {
            final MapType<String> dimensions = data.getMap("dimensions");

            if (dimensions == null) {
                return null;
            }

            for (final String dimension : dimensions.keys()) {
                final MapType<String> dimensionData = dimensions.getMap(dimension);
                if (dimensionData == null) {
                    continue;
                }

                final MapType<String> generator = dimensionData.getMap("generator");
                if (generator == null) {
                    continue;
                }

                final String type = generator.getString("type");
                if (type == null) {
                    continue;
                }

                switch (type) {
                    case "minecraft:flat": {
                        final MapType<String> settings = generator.getMap("settings");
                        if (settings == null) {
                            continue;
                        }

                        WalkerUtils.convert(MCTypeRegistry.BIOME, settings, "biome", fromVersion, toVersion);

                        final ListType layers = settings.getList("layers", ObjectType.MAP);
                        if (layers != null) {
                            for (int i = 0, len = layers.size(); i < len; ++i) {
                                WalkerUtils.convert(MCTypeRegistry.BLOCK_NAME, layers.getMap(i), "block", fromVersion, toVersion);
                            }
                        }

                        break;
                    }
                    case "minecraft:noise": {
                        final MapType<String> settings = generator.getMap("settings");
                        if (settings != null) {
                            WalkerUtils.convert(MCTypeRegistry.BLOCK_NAME, settings, "default_block", fromVersion, toVersion);
                            WalkerUtils.convert(MCTypeRegistry.BLOCK_NAME, settings, "default_fluid", fromVersion, toVersion);
                        }

                        final MapType<String> biomeSource = generator.getMap("biome_source");
                        if (biomeSource != null) {
                            final String biomeSourceType = biomeSource.getString("type", "");
                            switch (biomeSourceType) {
                                case "minecraft:fixed": {
                                    WalkerUtils.convert(MCTypeRegistry.BIOME, biomeSource, "biome", fromVersion, toVersion);
                                    break;
                                }

                                case "minecraft:multi_noise": {
                                    // preset is absent, no type for namespaced string

                                    // Vanilla's schema is _still_ wrong. It should be DSL.fields("biomes", DSL.list(DSL.fields("biome")))
                                    // But it just contains the list part. That obviously can never be the case, because
                                    // the root object is a compound, not a list.

                                    final ListType biomes = biomeSource.getList("biomes", ObjectType.MAP);
                                    if (biomes != null) {
                                        for (int i = 0, len = biomes.size(); i < len; ++i) {
                                            WalkerUtils.convert(MCTypeRegistry.BIOME, biomes.getMap(i), "biome", fromVersion, toVersion);
                                        }
                                    }
                                    break;
                                }

                                case "minecraft:checkerboard": {
                                    WalkerUtils.convertList(MCTypeRegistry.BIOME, biomeSource, "biomes", fromVersion, toVersion);
                                    break;
                                }
                            }
                        }

                        break;
                    }
                }
            }

            return null;
        });

        MCTypeRegistry.CHUNK.addStructureWalker(VERSION, (final MapType<String> data, final long fromVersion, final long toVersion) -> {
            final MapType<String> level = data.getMap("Level");
            if (level == null) {
                return null;
            }

            WalkerUtils.convertList(MCTypeRegistry.ENTITY, level, "Entities", fromVersion, toVersion);
            WalkerUtils.convertList(MCTypeRegistry.TILE_ENTITY, level, "TileEntities", fromVersion, toVersion);

            final ListType tileTicks = level.getList("TileTicks", ObjectType.MAP);
            if (tileTicks != null) {
                for (int i = 0, len = tileTicks.size(); i < len; ++i) {
                    final MapType<String> tileTick = tileTicks.getMap(i);
                    WalkerUtils.convert(MCTypeRegistry.BLOCK_NAME, tileTick, "i", fromVersion, toVersion);
                }
            }

            final ListType sections = level.getList("Sections", ObjectType.MAP);
            if (sections != null) {
                for (int i = 0, len = sections.size(); i < len; ++i) {
                    final MapType<String> section = sections.getMap(i);

                    WalkerUtils.convertList(MCTypeRegistry.BIOME, section.getMap("biomes"), "palette", fromVersion, toVersion);
                    WalkerUtils.convertList(MCTypeRegistry.BLOCK_STATE, section.getMap("block_states"), "palette", fromVersion, toVersion);
                }
            }

            WalkerUtils.convertValues(MCTypeRegistry.STRUCTURE_FEATURE, level.getMap("Structures"), "Starts", fromVersion, toVersion);

            return null;
        });
    }

    private static MapType<String> getEmptyBlockPalette() {
        final MapType<String> airBlockState = Types.NBT.createEmptyMap();
        airBlockState.setString("Name", "minecraft:air");

        final ListType emptyBlockPalette = Types.NBT.createEmptyList();
        emptyBlockPalette.addMap(airBlockState);

        return V2832.wrapPalette(emptyBlockPalette);
    }

    private static void shiftUpgradeData(final MapType<String> upgradeData, final int shift) {
        if (upgradeData == null) {
            return;
        }

        final MapType<String> indices = upgradeData.getMap("Indices");
        if (indices == null) {
            return;
        }

        RenameHelper.renameKeys(indices, (final String input) -> {
            return Integer.toString(Integer.parseInt(input) + shift);
        });
    }

    private static void updateChunkData(final MapType<String> level, final boolean wantExtendedHeight, final boolean isAlreadyExtended) {
        if (!wantExtendedHeight) {
            padCarvingMasks(level, 16, 0);
            return;
        }

        if (isAlreadyExtended) {
            padCarvingMasks(level, 24, 0);
            return;
        }

        offsetHeightmaps(level);
        addEmptyListPadding(level, "Lights");
        addEmptyListPadding(level, "LiquidsToBeTicked");
        addEmptyListPadding(level, "PostProcessing");
        addEmptyListPadding(level, "ToBeTicked");
        shiftUpgradeData(level.getMap("UpgradeData"), 4);
        padCarvingMasks(level, 24, 4);
    }

    private static void padCarvingMasks(final MapType<String> level, final int newSize, final int offset) {
        final MapType<String> carvingMasks = level.getMap("CarvingMasks");
        if (carvingMasks == null) {
            // if empty, DFU still writes
            level.setMap("CarvingMasks", Types.NBT.createEmptyMap());
            return;
        }

        for (final String key : carvingMasks.keys()) {
            final long[] old = carvingMasks.getLongs(key);
            final long[] newVal = new long[64 * newSize];

            System.arraycopy(old, 0, newVal, 64 * offset, old.length);

            carvingMasks.setLongs(key, newVal); // no CME: key exists already
        }
    }

    private static void addEmptyListPadding(final MapType<String> level, final String path) {
        ListType list = level.getListUnchecked(path);
        if (list != null && list.size() == 24) {
            return;
        }

        if (list == null) {
            // difference from DFU: Don't create the damn thing!
            return;
        }


        // offset the section array to the new format
        for (int i = 0; i < 4; ++i) {
            // always create new copies, so that modifying one doesn't modify ALL of them!
            list.addList(0, Types.NBT.createEmptyList()); // add below
            list.addList(Types.NBT.createEmptyList()); // add above
        }
    }

    private static void offsetHeightmaps(final MapType<String> level) {
        final MapType<String> heightmaps = level.getMap("Heightmaps");
        if (heightmaps == null) {
            return;
        }

        for (final String key : HEIGHTMAP_TYPES) {
            offsetHeightmap(heightmaps.getLongs(key));
        }
    }

    private static void offsetHeightmap(final long[] heightmap) {
        if (heightmap == null) {
            return;
        }

        // heightmaps are configured to have 9 bits per value, with 256 total values
        // heightmaps are also relative to the lowest position
        for (int idx = 0, len = heightmap.length; idx < len; ++idx) {
            long curr = heightmap[idx];
            long next = 0L;

            for (int objIdx = 0; objIdx + 9 <= 64; objIdx += 9) {
                final long value = (curr >> objIdx) & 511L;
                if (value != 0L) {
                    final long offset = Math.min(511L, value + 64L);

                    next |= (offset << objIdx);
                }
            }

            heightmap[idx] = next;
        }
    }

    private static MapType<String>[] createBiomeSections(final MapType<String> level, final boolean wantExtendedHeight,
                                                         final int minSection, final MutableBoolean isAlreadyExtended) {
        final MapType<String>[] ret = new MapType[wantExtendedHeight ? 24 : 16];

        final int[] biomes = level.getInts("Biomes");
        if (biomes == null) {
            final ListType palette = Types.NBT.createEmptyList();
            palette.addString("minecraft:plains");

            for (int i = 0; i < ret.length; ++i) {
                ret[i] = wrapPalette(palette.copy()); // copy palette so that later possible modifications don't trash all sections
            }

            return ret;
        }

        final boolean isExtended = biomes.length == 1536; // magic value for 24 sections of biomes (24 * 4^3)
        isAlreadyExtended.setValue(isExtended);

        if (isExtended) {
            for (int sectionIndex = 0; sectionIndex < 24; ++sectionIndex) {
                ret[sectionIndex] = createBiomeSection(biomes, sectionIndex * 64, -1); // -1 is all 1s
            }
        } else {
            for (int sectionY = 0; sectionY < 16; ++sectionY) {
                ret[sectionY - minSection] = createBiomeSection(biomes, sectionY * 64, -1); // -1 is all 1s
            }

            if (wantExtendedHeight) {
                // must set the new sections at top and bottom
                final MapType<String> bottomCopy = createBiomeSection(biomes, 0, 15); // just want the biomes at y = 0
                final MapType<String> topCopy = createBiomeSection(biomes, 1008, 15); // just want the biomes at y = 252

                for (int sectionIndex = 0; sectionIndex < 4; ++sectionIndex) {
                    ret[sectionIndex] = bottomCopy.copy(); // copy palette so that later possible modifications don't trash all sections
                }

                for (int sectionIndex = 20; sectionIndex < 24; ++sectionIndex) {
                    ret[sectionIndex] = topCopy.copy(); // copy palette so that later possible modifications don't trash all sections
                }
            }
        }

        return ret;
    }

    private static MapType<String> createBiomeSection(final int[] biomes, final int offset, final int mask) {
        final Int2IntLinkedOpenHashMap paletteId = new Int2IntLinkedOpenHashMap();

        for (int idx = 0; idx < 64; ++idx) {
            final int biome = biomes[offset + (idx & mask)];
            paletteId.putIfAbsent(biome, paletteId.size());
        }

        final ListType paletteString = Types.NBT.createEmptyList();
        for (final IntIterator iterator = paletteId.keySet().iterator(); iterator.hasNext();) {
            final int biomeId = iterator.nextInt();
            final String biome = biomeId >= 0 && biomeId < BIOMES_BY_ID.length ? BIOMES_BY_ID[biomeId] : null;
            paletteString.addString(biome == null ? "minecraft:plains" : biome);
        }

        final int bitsPerObject = ceilLog2(paletteString.size());
        if (bitsPerObject == 0) {
            return wrapPalette(paletteString);
        }

        // manually create packed integer data
        final int objectsPerValue = 64 / bitsPerObject;
        final long[] packed = new long[(64 + objectsPerValue - 1) / objectsPerValue];

        int shift = 0;
        int idx = 0;
        long curr = 0;

        for (int biome_idx = 0; biome_idx < 64; ++biome_idx) {
            final int biome = biomes[offset + (biome_idx & mask)];

            curr |= ((long)paletteId.get(biome)) << shift;

            shift += bitsPerObject;

            if (shift + bitsPerObject > 64) { // will next write overflow?
                // must move to next idx
                packed[idx++] = curr;
                shift = 0;
                curr = 0L;
            }
        }

        // don't forget to write the last one
        if (shift != 0) {
            packed[idx] = curr;
        }

        return wrapPalette(paletteString, packed);
    }

    private static MapType<String> wrapPalette(final ListType palette) {
        return wrapPalette(palette, null);
    }

    // start egregious hack
    // TODO remove this fix when https://bugs.mojang.com/browse/MC-238056 is resolved by Mojang
    private static void remap(final long[] val, int bitsPerObject, final int[] mapping, final boolean blocks) {
        bitsPerObject = blocks ? Math.max(4, bitsPerObject) : bitsPerObject;
        int objectsPerValue = 64 / bitsPerObject;
        final long mask = (1L << bitsPerObject) - 1; // works even if bitsPerObject == 64

        if (val.length != (((blocks ? 4096 : 64) + objectsPerValue - 1) / objectsPerValue)) {
            throw new IllegalStateException();
        }

        for (int i = 0; i < val.length; ++i) {
            long next = 0L;
            long curr = val[i];

            for (int objIdx = 0; objIdx + bitsPerObject <= 64; objIdx += bitsPerObject) {
                final long value = (curr >> objIdx) & mask;
                next |= ((long)mapping[(int)value]) << objIdx;
            }

            if (next != curr) {
                val[i] = next;
            }
        }
    }

    private static long[] resize(final long[] val, int oldBitsPerObject, int newBitsPerObject, final boolean blocks, final int max) {
        oldBitsPerObject = blocks ? Math.max(4, oldBitsPerObject) : oldBitsPerObject; // must be at least 4 if blocks
        newBitsPerObject = blocks ? Math.max(4, newBitsPerObject) : newBitsPerObject; // must be at least 4 if blocks
        final long oldMask = (1L << oldBitsPerObject) - 1; // works even if bitsPerObject == 64
        final long newMask = (1L << newBitsPerObject) - 1;
        final int oldObjectsPerValue = 64 / oldBitsPerObject;
        final int newObjectsPerValue = 64 / newBitsPerObject;

        if (newBitsPerObject == oldBitsPerObject) {
            return val;
        }

        final int items = blocks ? 4096 : 64; // 64 = 4096 / 4 / 4 / 4

        final long[] ret = new long[(items + newObjectsPerValue - 1) / newObjectsPerValue];

        if (val.length != ((items + oldObjectsPerValue - 1) / oldObjectsPerValue)) {
            throw new IllegalStateException();
        }

        int shift = 0;
        int idx = 0;
        long newCurr = 0L;

        int currItem = 0;
        for (int i = 0; i < val.length; ++i) {
            long oldCurr = val[i];

            for (int objIdx = 0; currItem < items && objIdx + oldBitsPerObject <= 64; objIdx += oldBitsPerObject, ++currItem) {
                final long value = (oldCurr >> objIdx) & oldMask;

                newCurr |= value << shift;
                shift += newBitsPerObject;

                if (value >= max) {
                    throw new IllegalStateException("wat");
                }

                if (shift + newBitsPerObject > 64) { // will next write overflow?
                    // must move to next idx
                    ret[idx++] = newCurr;
                    shift = 0;
                    newCurr = 0L;
                }
            }

            if (currItem == items && i != (val.length - 1)) {
                throw new IllegalStateException("wat");
            }
        }

        // don't forget to write the last one
        if (shift != 0) {
            ret[idx] = newCurr;
        }

        return ret;
    }
    // end egregious hack

    private static MapType<String> wrapPalette(final ListType palette, final long[] blockStates) {
        final MapType<String> ret = Types.NBT.createEmptyMap();
        ret.setList("palette", palette);
        if (blockStates != null) {
            ret.setLongs("data", blockStates);
            // TODO remove this fix when https://bugs.mojang.com/browse/MC-238056 is resolved by Mojang
            // start egregious hack
            // The new deserializer will fuck up duplicates, so let's fix this by eliminating them here AND CORRECTLY
            // modifying the data array
            final Object2IntLinkedOpenHashMap<Object> correctPalette = new Object2IntLinkedOpenHashMap<>();
            correctPalette.defaultReturnValue(-1);
            final int[] remap = new int[palette.size()];
            boolean broken = false;
            boolean blocks = palette.getGeneric(0) instanceof MapType;
            for (int i = 0, len = palette.size(); i < len; ++i) {
                final int current = correctPalette.size();
                final int prev = correctPalette.putIfAbsent(palette.getGeneric(i), current);
                remap[i] = prev == -1 ? current : prev;
                if (prev != -1) {
                    broken = true;
                }
            }
            if (broken) {
                // rewrite palette list
                final ListType newPalette = Types.NBT.createEmptyList();
                for (final Object val : correctPalette.keySet()) {
                    newPalette.addGeneric(val);
                }
                ret.setList("palette", newPalette);

                // rewrite the storage array
                remap(blockStates, ceilLog2(palette.size()), remap, blocks);

                if (ceilLog2(newPalette.size()) != ceilLog2(palette.size())) {
                    // must resize the block storage too...
                    ret.setLongs("data", resize(blockStates, ceilLog2(palette.size()), ceilLog2(newPalette.size()), blocks, newPalette.size()));
                }
            }
            // end egregious hack
        }

        return ret;
    }

    private static MapType<String> wrapPaletteOptimised(final ListType palette, final long[] blockStates) {
        if (palette.size() == 1) {
            return wrapPalette(palette);
        }

        return wrapPalette(palette, blockStates);
    }

    public static int ceilLog2(final int value) {
        return value == 0 ? 0 : Integer.SIZE - Integer.numberOfLeadingZeros(value - 1); // see doc of numberOfLeadingZeros
    }

    private static void updateLayers(final ListType layers) {
        if (layers == null) {
            return;
        }

        layers.addMap(0, createEmptyLayer()); // add at the bottom
    }

    private static MapType<String> createEmptyLayer() {
        final MapType<String> ret = Types.NBT.createEmptyMap();
        ret.setInt("height", 64);
        ret.setString("block", "minecraft:air");

        return ret;
    }
}
