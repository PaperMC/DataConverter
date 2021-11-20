package ca.spottedleaf.dataconverter.minecraft;

import ca.spottedleaf.dataconverter.converters.datatypes.DataType;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.datafix.fixes.References;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ReplacedDataFixerUpper implements DataFixer {

    protected static final Set<DSL.TypeReference> WARNED_TYPES = ConcurrentHashMap.newKeySet();

    private static final Logger LOGGER = LogManager.getLogger();

    public final DataFixer wrapped;

    public ReplacedDataFixerUpper(final DataFixer wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public <T> Dynamic<T> update(final DSL.TypeReference type, final Dynamic<T> input, final int version, final int newVersion) {
        DataType<?, ?> equivType = null;
        boolean warn = true;

        if (type == References.LEVEL) {
            warn = false;
        }
        if (type == References.PLAYER) {
            equivType = MCTypeRegistry.PLAYER;
        }
        if (type == References.CHUNK) {
            equivType = MCTypeRegistry.CHUNK;
        }
        if (type == References.HOTBAR) {
            warn = false;
        }
        if (type == References.OPTIONS) {
            warn = false;
        }
        if (type == References.STRUCTURE) {
            equivType = MCTypeRegistry.STRUCTURE;
        }
        if (type == References.STATS) {
            warn = false;
        }
        if (type == References.SAVED_DATA) {
            equivType = MCTypeRegistry.SAVED_DATA;
        }
        if (type == References.ADVANCEMENTS) {
            warn = false;
        }
        if (type == References.POI_CHUNK) {
            equivType = MCTypeRegistry.POI_CHUNK;
        }
        if (type == References.ENTITY_CHUNK) {
            equivType = MCTypeRegistry.ENTITY_CHUNK;
        }
        if (type == References.BLOCK_ENTITY) {
            equivType = MCTypeRegistry.TILE_ENTITY;
        }
        if (type == References.ITEM_STACK) {
            equivType = MCTypeRegistry.ITEM_STACK;
        }
        if (type == References.BLOCK_STATE) {
            equivType = MCTypeRegistry.BLOCK_STATE;
        }
        if (type == References.ENTITY_NAME) {
            equivType = MCTypeRegistry.ENTITY_NAME;
        }
        if (type == References.ENTITY_TREE) {
            equivType = MCTypeRegistry.ENTITY;
        }
        if (type == References.ENTITY) {
            // NO EQUIV TYPE (this is ENTITY without passengers/riding)
            // Only used internally for DFU, so we shouldn't get here
        }
        if (type == References.BLOCK_NAME) {
            equivType = MCTypeRegistry.BLOCK_NAME;
        }
        if (type == References.ITEM_NAME) {
            equivType = MCTypeRegistry.ITEM_NAME;
        }
        if (type == References.UNTAGGED_SPAWNER) {
            equivType = MCTypeRegistry.UNTAGGED_SPAWNER;
        }
        if (type == References.STRUCTURE_FEATURE) {
            equivType = MCTypeRegistry.STRUCTURE_FEATURE;
        }
        if (type == References.OBJECTIVE) {
            warn = false;
        }
        if (type == References.TEAM) {
            warn = false;
        }
        if (type == References.RECIPE) {
            warn = false;
        }
        if (type == References.BIOME) {
            equivType = MCTypeRegistry.BIOME;
        }
        if (type == References.WORLD_GEN_SETTINGS) {
            warn = false;
        }

        if (equivType != null) {
            if (newVersion > version) {
                try {
                    final Dynamic<T> ret = new Dynamic<>(input.getOps(), (T)MCDataConverter.copy(MCDataConverter.convertUnwrapped((DataType)equivType, input.getValue(), false, version, newVersion)));
                    return ret;
                } catch (final Exception ex) {
                    LOGGER.error("Failed to convert data using DataConverter, falling back to DFU", new Throwable());
                    if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
                        // In debug this is a HARD FAIL
                        System.exit(0);
                    }
                }

                return this.wrapped.update(type, input, version, newVersion);
            } else {
                return input;
            }
        } else {
            if (warn && WARNED_TYPES.add(type)) {
                LOGGER.error("No equiv type for " + type, new Throwable());
            }

            return this.wrapped.update(type, input, version, newVersion);
        }
    }

    @Override
    public Schema getSchema(final int key) {
        return this.wrapped.getSchema(key);
    }
}
