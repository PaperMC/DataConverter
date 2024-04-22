package ca.spottedleaf.dataconverter.util;

import org.jetbrains.annotations.NotNull;

import java.util.List;

// Mini version of guava splitter in my desperate attempt to avoid depending on guava
public class Splitter {

    public static Splitter on(char c) {
        return new Splitter(c);
    }

    private final char c;
    private int limit = -1;

    private Splitter(char c) {
        this.c = c;
    }

    public @NotNull Splitter limit(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("limit must be positive");
        }
        this.limit = count;
        return this;
    }

    public Iterable<String> split(String input) {
        return splitToList(input);
    }

    public List<String> splitToList(String input) {
        return splitToList(input, limit);
    }

    public List<String> splitToList(String input, int limit) {
        if (input.isEmpty()) {
            return List.of();
        }

        if (limit == 1) {
            return List.of(input);
        }

        var result = new java.util.ArrayList<String>();

        int start = 0;
        while (true) {
            int end = input.indexOf(c, start);
            if (end == -1) {
                break;
            }

            if (limit > 0 && result.size() + 1 >= limit) {
                end = input.length();
            }

            result.add(input.substring(start, end));
            start = end + 1;
        }

        result.add(input.substring(start));
        return result;
    }

}
