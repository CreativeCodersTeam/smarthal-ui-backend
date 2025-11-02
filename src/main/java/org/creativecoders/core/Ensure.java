package org.creativecoders.core;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Objects;

public class Ensure {

    private Ensure() {
    }

    public static <T> @Nonnull T notNull(@Nonnull T obj) {
        return Objects.requireNonNull(obj);
    }

    public static <T> T notNull(T obj, String paramName) {
        return Objects.requireNonNull(obj, "Parameter must not be null: " + paramName);
    }

    public static String notNullOrEmpty(@Nullable String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("String must not be null or empty");
        }

        return str;
    }
}
