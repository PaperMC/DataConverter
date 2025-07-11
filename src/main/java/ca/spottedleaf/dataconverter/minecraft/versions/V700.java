package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.types.MapType;

public final class V700 {

    private static final int VERSION = MCVersions.V1_10_2 + 188;

    public static void register() {
        MCTypeRegistry.ENTITY.addConverterForId("Guardian", new DataConverter<>(VERSION) {
            @Override
            public MapType convert(final MapType data, final long sourceVersion, final long toVersion) {
                if (data.getBoolean("Elder")) {
                    data.setString("id", "ElderGuardian");
                }
                data.remove("Elder");
                return null;
            }
        });

        //registerMob("ElderGuardian"); // is now simple in 1.21.5
    }

    private V700() {}
}
