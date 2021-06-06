package ca.spottedleaf.dataconverter.common.minecraft.datatypes;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.converters.datatypes.DataWalker;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersionRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;
import ca.spottedleaf.dataconverter.common.util.Long2ObjectArraySortedMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IDDataType extends MCDataType {

    protected final Map<String, Long2ObjectArraySortedMap<List<DataWalker<String>>>> walkersById = new HashMap<>();

    public IDDataType(final String name) {
        super(name);
    }

    public void addConverterForId(final String id, final DataConverter<MapType<String>, MapType<String>> converter) {
        this.addStructureConverter(new DataConverter<>(converter.getToVersion(), converter.getVersionStep()) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                if (!id.equals(data.getString("id"))) {
                    return null;
                }
                return converter.convert(data, sourceVersion, toVersion);
            }
        });
    }

    public void addWalker(final int minVersion, final String id, final DataWalker<String> walker) {
        this.addWalker(minVersion, 0, id, walker);
    }

    public void addWalker(final int minVersion, final int versionStep, final String id, final DataWalker<String> walker) {
        this.walkersById.computeIfAbsent(id, (final String keyInMap) -> {
            return new Long2ObjectArraySortedMap<>();
        }).computeIfAbsent(DataConverter.encodeVersions(minVersion, versionStep), (final long keyInMap) -> {
            return new ArrayList<>();
        }).add(walker);
    }

    public void copyWalkers(final int minVersion, final String fromId, final String toId) {
        this.copyWalkers(minVersion, 0, fromId, toId);
    }

    public void copyWalkers(final int minVersion, final int versionStep, final String fromId, final String toId) {
        final long version = DataConverter.encodeVersions(minVersion, versionStep);
        final Long2ObjectArraySortedMap<List<DataWalker<String>>> walkersForId = this.walkersById.get(fromId);
        if (walkersForId == null) {
            return;
        }

        final List<DataWalker<String>> nearest = walkersForId.getFloor(version);

        if (nearest == null) {
            return;
        }

        for (final DataWalker<String> walker : nearest) {
            this.addWalker(minVersion, versionStep, toId, walker);
        }
    }

    @Override
    public MapType<String> convert(MapType<String> data, final long fromVersion, final long toVersion) {
        MapType<String> ret = null;

        final List<DataConverter<MapType<String>, MapType<String>>> converters = this.structureConverters;
        for (int i = 0, len = converters.size(); i < len; ++i) {
            final DataConverter<MapType<String>, MapType<String>> converter = converters.get(i);
            final long converterVersion = converter.getEncodedVersion();

            if (converterVersion <= fromVersion) {
                continue;
            }

            if (converterVersion > toVersion) {
                break;
            }

            final MapType<String> replace = converter.convert(data, fromVersion, toVersion);
            if (replace != null) {
                ret = data = replace;
            }
        }

        final List<DataWalker<String>> walkers = this.structureWalkers.getFloor(toVersion);
        if (walkers != null) {
            for (int i = 0, len = walkers.size(); i < len; ++i) {
                walkers.get(i).walk(data, fromVersion, toVersion);
            }
        }

        final Long2ObjectArraySortedMap<List<DataWalker<String>>> walkersByVersion = this.walkersById.get(data.getString("id"));
        if (walkersByVersion != null) {
            final List<DataWalker<String>> walkersForId = walkersByVersion.getFloor(toVersion);
            if (walkersForId != null) {
                for (int i = 0, len = walkersForId.size(); i < len; ++i) {
                    walkersForId.get(i).walk(data, fromVersion, toVersion);
                }
            }
        }

        return ret;
    }
}
