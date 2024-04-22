package ca.spottedleaf.dataconverter.types.nbt;

import ca.spottedleaf.dataconverter.types.*;
import net.kyori.adventure.nbt.*;

import java.util.Set;

public final class NBTMapType implements MapType<String> {

    private CompoundBinaryTag map;

    public NBTMapType() {
        this.map = CompoundBinaryTag.empty();
    }

    public NBTMapType(final CompoundBinaryTag tag) {
        this.map = tag;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != NBTMapType.class) {
            return false;
        }

        return this.map.equals(((NBTMapType) obj).map);
    }

    @Override
    public TypeUtil getTypeUtil() {
        return Types.NBT;
    }

    @Override
    public int hashCode() {
        return this.map.hashCode();
    }

    @Override
    public String toString() {
        return "NBTMapType{" +
                "map=" + this.map +
                '}';
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.size() == 0;
    }

    @Override
    public void clear() {
        this.map = CompoundBinaryTag.empty();
    }

    @Override
    public Set<String> keys() {
        return this.map.keySet();
    }

    public BinaryTag getTag() {
        return this.map;
    }

    @Override
    public MapType<String> copy() {
        return new NBTMapType(this.map);
    }

    @Override
    public boolean hasKey(final String key) {
        return this.map.get(key) != null;
    }

    @Override
    public boolean hasKey(final String key, final ObjectType type) {
        final BinaryTag tag = this.map.get(key);
        if (tag == null) {
            return false;
        }

        final ObjectType valueType = NBTListType.getType(tag.type().id());

        return valueType == type || (type == ObjectType.NUMBER && valueType.isNumber());
    }

    @Override
    public void remove(final String key) {
        this.map = this.map.remove(key);
    }

    @Override
    public Object getGeneric(final String key) {
        final BinaryTag tag = this.map.get(key);
        if (tag == null) {
            return null;
        }

        switch (NBTListType.getType(tag.type().id())) {
            case BYTE:
            case SHORT:
            case INT:
            case LONG:
            case FLOAT:
            case DOUBLE:
                return ((DoubleBinaryTag) tag).value();
            case MAP:
                return new NBTMapType((CompoundBinaryTag) tag);
            case LIST:
                return new NBTListType((ListBinaryTag) tag);
            case STRING:
                return ((StringBinaryTag) tag).value();
            case BYTE_ARRAY:
                return ((ByteArrayBinaryTag) tag).value();
            // Note: No short array tag!
            case INT_ARRAY:
                return ((IntArrayBinaryTag) tag).value();
            case LONG_ARRAY:
                return ((LongArrayBinaryTag) tag).value();
        }

        throw new IllegalStateException("Unrecognized type " + tag);
    }

    @Override
    public Number getNumber(final String key) {
        return this.getNumber(key, null);
    }

    @Override
    public Number getNumber(final String key, final Number dfl) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof NumberBinaryTag) {
            switch (tag) {
                case ByteBinaryTag byteTag:
                    return byteTag.value();
                case ShortBinaryTag shortTag:
                    return shortTag.value();
                case IntBinaryTag intTag:
                    return intTag.value();
                case LongBinaryTag longTag:
                    return longTag.value();
                case FloatBinaryTag floatTag:
                    return floatTag.value();
                case DoubleBinaryTag doubleTag:
                    return doubleTag.value();
                default:
                    throw new IllegalStateException("Unrecognized type " + tag);
            }
        }
        return dfl;
    }

    @Override
    public boolean getBoolean(final String key) {
        return this.getByte(key) != 0;
    }

    @Override
    public boolean getBoolean(final String key, final boolean dfl) {
        return this.getByte(key, dfl ? (byte) 1 : (byte) 0) != 0;
    }

    @Override
    public void setBoolean(final String key, final boolean val) {
        this.setByte(key, val ? (byte) 1 : (byte) 0);
    }

    @Override
    public byte getByte(final String key) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof NumberBinaryTag) {
            return ((NumberBinaryTag) tag).byteValue();
        }
        return 0;
    }

    @Override
    public byte getByte(final String key, final byte dfl) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof NumberBinaryTag) {
            return ((NumberBinaryTag) tag).byteValue();
        }
        return dfl;
    }

    @Override
    public void setByte(final String key, final byte val) {
        this.map = this.map.putByte(key, val);
    }

    @Override
    public short getShort(final String key) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof NumberBinaryTag) {
            return ((NumberBinaryTag) tag).shortValue();
        }
        return 0;
    }

    @Override
    public short getShort(final String key, final short dfl) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof NumberBinaryTag) {
            return ((NumberBinaryTag) tag).shortValue();
        }
        return dfl;
    }

    @Override
    public void setShort(final String key, final short val) {
        this.map = this.map.putShort(key, val);
    }

    @Override
    public int getInt(final String key) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof NumberBinaryTag) {
            return ((NumberBinaryTag) tag).intValue();
        }
        return 0;
    }

    @Override
    public int getInt(final String key, final int dfl) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof NumberBinaryTag) {
            return ((NumberBinaryTag) tag).intValue();
        }
        return dfl;
    }

    @Override
    public void setInt(final String key, final int val) {
        this.map = this.map.putInt(key, val);
    }

    @Override
    public long getLong(final String key) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof NumberBinaryTag) {
            return ((NumberBinaryTag) tag).longValue();
        }
        return 0;
    }

    @Override
    public long getLong(final String key, final long dfl) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof NumberBinaryTag) {
            return ((NumberBinaryTag) tag).longValue();
        }
        return dfl;
    }

    @Override
    public void setLong(final String key, final long val) {
        this.map = this.map.putLong(key, val);
    }

    @Override
    public float getFloat(final String key) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof NumberBinaryTag) {
            return ((NumberBinaryTag) tag).floatValue();
        }
        return 0;
    }

    @Override
    public float getFloat(final String key, final float dfl) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof NumberBinaryTag) {
            return ((NumberBinaryTag) tag).floatValue();
        }
        return dfl;
    }

    @Override
    public void setFloat(final String key, final float val) {
        this.map = this.map.putFloat(key, val);
    }

    @Override
    public double getDouble(final String key) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof NumberBinaryTag) {
            return ((NumberBinaryTag) tag).doubleValue();
        }
        return 0;
    }

    @Override
    public double getDouble(final String key, final double dfl) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof NumberBinaryTag) {
            return ((NumberBinaryTag) tag).doubleValue();
        }
        return dfl;
    }

    @Override
    public void setDouble(final String key, final double val) {
        this.map = this.map.putDouble(key, val);
    }

    @Override
    public byte[] getBytes(final String key) {
        return this.getBytes(key, null);
    }

    @Override
    public byte[] getBytes(final String key, final byte[] dfl) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof ByteArrayBinaryTag) {
            return ((ByteArrayBinaryTag) tag).value();
        }
        return dfl;
    }

    @Override
    public void setBytes(final String key, final byte[] val) {
        this.map = this.map.putByteArray(key, val);
    }

    @Override
    public short[] getShorts(final String key) {
        return this.getShorts(key, null);
    }

    @Override
    public short[] getShorts(final String key, final short[] dfl) {
        // NBT does not support short array
        return dfl;
    }

    @Override
    public void setShorts(final String key, final short[] val) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int[] getInts(final String key) {
        return this.getInts(key, null);
    }

    @Override
    public int[] getInts(final String key, final int[] dfl) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof IntArrayBinaryTag) {
            return ((IntArrayBinaryTag) tag).value();
        }
        return dfl;
    }

    @Override
    public void setInts(final String key, final int[] val) {
        this.map = this.map.putIntArray(key, val);
    }

    @Override
    public long[] getLongs(final String key) {
        return this.getLongs(key, null);
    }

    @Override
    public long[] getLongs(final String key, final long[] dfl) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof LongArrayBinaryTag) {
            return ((LongArrayBinaryTag) tag).value();
        }
        return dfl;
    }

    @Override
    public void setLongs(final String key, final long[] val) {
        this.map = this.map.putLongArray(key, val);
    }

    @Override
    public ListType getListUnchecked(final String key) {
        return this.getListUnchecked(key, null);
    }

    @Override
    public ListType getListUnchecked(final String key, final ListType dfl) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof ListBinaryTag) {
            return new NBTListType((ListBinaryTag) tag);
        }
        return dfl;
    }

    @Override
    public void setList(final String key, final ListType val) {
        this.map = this.map.put(key, ((NBTListType) val).getTag());
    }

    @Override
    public MapType<String> getMap(final String key) {
        return this.getMap(key, null);
    }

    @Override
    public MapType<String> getMap(final String key, final MapType dfl) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof CompoundBinaryTag) {
            return new NBTMapType((CompoundBinaryTag) tag);
        }
        return dfl;
    }

    @Override
    public void setMap(final String key, final MapType<?> val) {
        this.map = this.map.put(key, ((NBTMapType) val).getTag());
    }

    @Override
    public String getString(final String key) {
        return this.getString(key, null);
    }

    @Override
    public String getString(final String key, final String dfl) {
        final BinaryTag tag = this.map.get(key);
        if (tag instanceof StringBinaryTag) {
            return ((StringBinaryTag) tag).value();
        }
        return dfl;
    }

    @Override
    public String getForcedString(final String key) {
        return this.getForcedString(key, null);
    }

    @Override
    public String getForcedString(final String key, final String dfl) {
        final BinaryTag tag = this.map.get(key);
        if (tag != null) {
            return ((StringBinaryTag) tag).value();
        }
        return dfl;
    }

    @Override
    public void setString(final String key, final String val) {
        this.map = this.map.putString(key, val);
    }
}
