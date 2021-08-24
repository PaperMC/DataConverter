package ca.spottedleaf.dataconverter.common.minecraft.hooks;

import ca.spottedleaf.dataconverter.common.converters.datatypes.DataHook;
import ca.spottedleaf.dataconverter.common.util.NamespaceUtil;

public class DataHookValueTypeEnforceNamespaced implements DataHook<Object, Object> {

    @Override
    public Object preHook(final Object data, final long fromVersion, final long toVersion) {
        if (data instanceof String) {
            return NamespaceUtil.correctNamespaceOrNull((String)data);
        }
        return null;
    }

    @Override
    public Object postHook(final Object data, final long fromVersion, final long toVersion) {
        return null;
    }
}
