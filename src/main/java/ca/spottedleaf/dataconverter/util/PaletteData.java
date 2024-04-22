package ca.spottedleaf.dataconverter.util;

import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import org.jetbrains.annotations.NotNull;

// Botched version of https://github.com/Minestom/Minestom/blob/e8e22a2b15512168a20b79e8b85ec20184a1e029/src/main/java/net/minestom/server/instance/palette/FlexiblePalette.java#L19, licensed under MIT
public class PaletteData {

    private byte bitsPerEntry;
    private int count;
    private int size;

    private long[] values;
    // palette index = value
    IntArrayList paletteToValueList;
    // value = palette index
    private Int2IntOpenHashMap valueToPaletteMap;

    public PaletteData(int bitsPerEntry, int size) {
        this(bitsPerEntry, size, new long[(size + (64 / bitsPerEntry) - 1) / (64 / bitsPerEntry)]);
    }

    public PaletteData(int bitsPerEntry, int size, long[] states) {
        this.bitsPerEntry = (byte) bitsPerEntry;

        this.paletteToValueList = new IntArrayList(1);
        this.paletteToValueList.add(0);
        this.valueToPaletteMap = new Int2IntOpenHashMap(1);
        this.valueToPaletteMap.put(0, 0);
        this.valueToPaletteMap.defaultReturnValue(-1);

        final int valuesPerLong = 64 / this.bitsPerEntry;
        this.values = new long[(size + valuesPerLong - 1) / valuesPerLong];
    }

    public int getBits() {
        return bitsPerEntry;
    }

    public int get(int sectionIndex) {
        final int bitsPerEntry = this.bitsPerEntry;
        final int valuesPerLong = 64 / bitsPerEntry;
        final int index = sectionIndex / valuesPerLong;
        final int bitIndex = (sectionIndex - index * valuesPerLong) * bitsPerEntry;
        final int value = (int) (values[index] >> bitIndex) & ((1 << bitsPerEntry) - 1);
        // Change to palette value and return
        return hasPalette() ? paletteToValueList.getInt(value) : value;
    }

    public void set(int x, int y, int z, int value) {
        set(getSectionIndex(16, x, y, z), value);
    }

    public void set(int sectionIndex, int value) {
        value = getPaletteIndex(value);
        final int bitsPerEntry = this.bitsPerEntry;
        final long[] values = this.values;
        // Change to palette value
        final int valuesPerLong = 64 / bitsPerEntry;
        final int index = sectionIndex / valuesPerLong;
        final int bitIndex = (sectionIndex - index * valuesPerLong) * bitsPerEntry;

        final long block = values[index];
        final long clear = (1L << bitsPerEntry) - 1L;
        final long oldBlock = block >> bitIndex & clear;
        values[index] = block & ~(clear << bitIndex) | ((long) value << bitIndex);
        // Check if block count needs to be updated
        final boolean currentAir = oldBlock == 0;
        if (currentAir != (value == 0)) this.count += currentAir ? 1 : -1;
    }

    private int getPaletteIndex(int value) {
        if (!hasPalette()) return value;
        final int lastPaletteIndex = this.paletteToValueList.size();
        final byte bpe = this.bitsPerEntry;
        if (lastPaletteIndex >= maxPaletteSize(bpe)) {
            // Palette is full, must resize
            resize((byte) (bpe + 1));
            return getPaletteIndex(value);
        }
        final int lookup = valueToPaletteMap.putIfAbsent(value, lastPaletteIndex);
        if (lookup != -1) return lookup;
        this.paletteToValueList.add(value);
        assert lastPaletteIndex < maxPaletteSize(bpe);
        return lastPaletteIndex;
    }

    void resize(byte newBitsPerEntry) {
        newBitsPerEntry = newBitsPerEntry > 8 ? 15 : newBitsPerEntry;
        PaletteData palette = new PaletteData(size, newBitsPerEntry);
        palette.paletteToValueList = paletteToValueList;
        palette.valueToPaletteMap = valueToPaletteMap;
        getAll(palette::set);
        this.bitsPerEntry = palette.bitsPerEntry;
        this.values = palette.values;
        assert this.count == palette.count;
    }

    public long[] getRaw() {
        return values;
    }

    public boolean hasPalette() {
        return bitsPerEntry <= 8;
    }

    static int maxPaletteSize(int bitsPerEntry) {
        return 1 << bitsPerEntry;
    }

    @FunctionalInterface
    interface EntryConsumer {
        void accept(int x, int y, int z, int value);
    }

    public void getAll(@NotNull EntryConsumer consumer) {
        retrieveAll(consumer, true);
    }

    private void retrieveAll(@NotNull EntryConsumer consumer, boolean consumeEmpty) {
        if (!consumeEmpty && count == 0) return;
        final long[] values = this.values;
        final int dimension = 16;
        final int bitsPerEntry = this.bitsPerEntry;
        final int magicMask = (1 << bitsPerEntry) - 1;
        final int valuesPerLong = 64 / bitsPerEntry;
        final int size = 4096;
        final int dimensionMinus = dimension - 1;
        final int[] ids = hasPalette() ? paletteToValueList.elements() : null;
        final int dimensionBitCount = IntegerUtil.ceilLog2(dimensionMinus);
        final int shiftedDimensionBitCount = dimensionBitCount << 1;
        for (int i = 0; i < values.length; i++) {
            final long value = values[i];
            final int startIndex = i * valuesPerLong;
            final int endIndex = Math.min(startIndex + valuesPerLong, size);
            for (int index = startIndex; index < endIndex; index++) {
                final int bitIndex = (index - startIndex) * bitsPerEntry;
                final int paletteIndex = (int) (value >> bitIndex & magicMask);
                if (consumeEmpty || paletteIndex != 0) {
                    final int y = index >> shiftedDimensionBitCount;
                    final int z = index >> dimensionBitCount & dimensionMinus;
                    final int x = index & dimensionMinus;
                    final int result = ids != null && paletteIndex < ids.length ? ids[paletteIndex] : paletteIndex;
                    consumer.accept(x, y, z, result);
                }
            }
        }
    }

    static int getSectionIndex(int dimension, int x, int y, int z) {
        final int dimensionMask = dimension - 1;
        final int dimensionBitCount = IntegerUtil.ceilLog2(dimensionMask);
        return (y & dimensionMask) << (dimensionBitCount << 1) |
                (z & dimensionMask) << dimensionBitCount |
                (x & dimensionMask);
    }
}
