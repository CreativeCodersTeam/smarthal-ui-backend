package org.creativecoders.core;

public class Ensure {

    private Ensure() {
    }

    public static <T> T ensureNotNull(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        return obj;
    }

    public static <T> T ensureNotNull(T obj, String paramName) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument cannot be null for parameter: " + paramName);
        }

        return obj;
    }
}
