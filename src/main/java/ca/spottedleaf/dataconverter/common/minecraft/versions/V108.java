package ca.spottedleaf.dataconverter.common.minecraft.versions;

import ca.spottedleaf.dataconverter.common.converters.DataConverter;
import ca.spottedleaf.dataconverter.common.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.common.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.common.types.MapType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.UUID;

public final class V108 {

    private static final Logger LOGGER = LogManager.getLogger();

    protected static final int VERSION = MCVersions.V15W32C + 4;

    public static void register() {
        // Convert String UUID into UUIDMost and UUIDLeast
        MCTypeRegistry.ENTITY.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final String uuidString = data.getString("UUID");

                if (uuidString == null) {
                    return null;
                }
                data.remove("UUID");

                final UUID uuid;
                try {
                    uuid = UUID.fromString(uuidString);
                } catch (final Exception ex) {
                    LOGGER.warn("Failed to parse UUID for legacy entity (V108): " + data, ex);
                    return null;
                }

                data.setLong("UUIDMost", uuid.getMostSignificantBits());
                data.setLong("UUIDLeast", uuid.getLeastSignificantBits());

                return null;
            }
        });
    }

    private V108() {}

}
