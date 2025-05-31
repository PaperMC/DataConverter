package ca.spottedleaf.dataconverter.minecraft.converters.advancements;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.converters.helpers.RenameHelper;
import ca.spottedleaf.dataconverter.types.MapType;
import java.util.function.Function;

public final class ConverterCriteriaRename extends DataConverter<MapType, MapType> {

    public final String path;
    public final Function<String, String> conversion;

    public ConverterCriteriaRename(final int toVersion, final String path, final Function<String, String> conversion) {
        super(toVersion);
        this.path = path;
        this.conversion = conversion;
    }

    public ConverterCriteriaRename(final int toVersion, final int versionStep, final String path, final Function<String, String> conversion) {
        super(toVersion, versionStep);
        this.path = path;
        this.conversion = conversion;
    }

    @Override
    public MapType convert(final MapType data, final long sourceVersion, final long toVersion) {
        final MapType advancement = data.getMap(this.path);
        if (advancement == null) {
            return null;
        }

        final MapType criteria = advancement.getMap("criteria");
        if (criteria == null) {
            return null;
        }

        RenameHelper.renameKeys(criteria, this.conversion);

        return null;
    }

}
