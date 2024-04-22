package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.minecraft.util.ComponentUtils;
import ca.spottedleaf.dataconverter.types.MapType;

public final class V1514 {

    private static final int VERSION = MCVersions.V1_13_PRE7 + 1;

    public static void register() {
        MCTypeRegistry.OBJECTIVE.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final String displayName = data.getString("DisplayName");
                if (displayName == null) {
                    return null;
                }

                final String update = ComponentUtils.createPlainTextComponent(displayName);

                data.setString("DisplayName", update);

                return null;
            }
        });

        MCTypeRegistry.TEAM.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final String displayName = data.getString("DisplayName");
                if (displayName == null) {
                    return null;
                }

                final String update = ComponentUtils.createPlainTextComponent(displayName);

                data.setString("DisplayName", update);

                return null;
            }
        });

        MCTypeRegistry.OBJECTIVE.addStructureConverter(new DataConverter<>(VERSION) {
            private static String getRenderType(String string) {
                return string.equals("health") ? "hearts" : "integer";
            }

            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final String renderType = data.getString("RenderType");
                if (renderType != null) {
                    return null;
                }

                final String criteriaName = data.getString("CriteriaName", "");

                data.setString("RenderType", getRenderType(criteriaName));

                return null;
            }
        });
    }

    private V1514() {}
}
