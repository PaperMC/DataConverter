package ca.spottedleaf.dataconverter.common.types;

import ca.spottedleaf.dataconverter.common.types.json.JsonTypeCompressedUtil;
import ca.spottedleaf.dataconverter.common.types.json.JsonTypeUtil;
import ca.spottedleaf.dataconverter.common.types.nbt.NBTTypeUtil;

public interface Types {

    public static final TypeUtil NBT = new NBTTypeUtil();

    public static final TypeUtil JSON = new JsonTypeUtil();

    // why does this exist
    public static final TypeUtil JSON_COMPRESSED = new JsonTypeCompressedUtil();
}
