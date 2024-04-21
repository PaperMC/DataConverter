package ca.spottedleaf.dataconverter.util;

import org.jetbrains.annotations.NotNull;

import java.util.List;

// Mini version of guava splitter in my desperate attempt to avoid depending on guava
public class Splitter {

    public static Splitter on(char c) {
        throw new UnsupportedOperationException("not implemented");
    }

    public @NotNull Splitter limit(int count) {
        throw new UnsupportedOperationException("not implemented");
    }

    public Iterable<String> split(String input) {
        throw new UnsupportedOperationException("not implemented");
    }

    public List<String> splitToList(String input) {
        throw new UnsupportedOperationException("not implemented");
    }
}
