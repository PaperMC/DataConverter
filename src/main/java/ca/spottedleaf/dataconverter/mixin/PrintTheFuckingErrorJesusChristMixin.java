package ca.spottedleaf.dataconverter.mixin;

import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.util.Either;
import net.minecraft.ReportedException;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.storage.ChunkStorage;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import java.io.IOException;
import java.nio.file.Path;

@Mixin(ChunkMap.class)
public abstract class PrintTheFuckingErrorJesusChristMixin extends ChunkStorage implements ChunkHolder.PlayerProvider {

    @Shadow
    protected abstract void markPositionReplaceable(ChunkPos chunkPos);

    @Shadow
    @Final
    private static Logger LOGGER;

    @Shadow
    protected abstract ChunkAccess createEmptyChunk(ChunkPos chunkPos);

    public PrintTheFuckingErrorJesusChristMixin(Path path, DataFixer dataFixer, boolean bl) {
        super(path, dataFixer, bl);
    }

    /**
     * @reason Print the fucking exception jesus christ how hard is it:
     * 1. handle or
     * 2. print or
     * 3. rethrow
     * @author Spottedleaf
     */
    @Overwrite
    private Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure> handleChunkLoadFailure(Throwable throwable, ChunkPos chunkPos) {
        if (throwable instanceof ReportedException reportedException) {
            Throwable throwable2 = reportedException.getCause();
            if (!(throwable2 instanceof IOException)) {
                this.markPositionReplaceable(chunkPos);
                throw reportedException;
            }

            LOGGER.error("Couldn't load chunk {}", chunkPos, throwable2);
        } else if (throwable instanceof IOException) {
            LOGGER.error("Couldn't load chunk {}", chunkPos, throwable);
        }

        LOGGER.error("Couldn't load chunk {} (Vanilla swallowed exception)", chunkPos, throwable);

        return Either.left(this.createEmptyChunk(chunkPos));
    }
}
