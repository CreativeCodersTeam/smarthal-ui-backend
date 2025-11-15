package net.creativecoders.core;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class EnsureTests implements WithAssertions {

    @Test
    void ensureNotNull_NonNullValue_ReturnsSameObject() {
        assertThat(Ensure.notNull(1)).isEqualTo(1);
    }

    @Test
    void ensureNotNull_NonNullValueWithMessage_ReturnsSameObject() {
        assertThat(Ensure.notNull(1, "one")).isEqualTo(1);
    }

    @Test
    void ensureNotNullWithMessage() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> Ensure.notNull(null, "null"))
                .withMessage("Parameter must not be null: null");
    }

    @Test
    void ensureNotNullOrEmpty() {
        assertThat(Ensure.notNullOrEmpty("test")).isEqualTo("test");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void ensureNotNullOrEmptyWithMessage(String value) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Ensure.notNullOrEmpty(value, "null"))
                .withMessage("String parameter must not be null or empty: null");
    }

    @Test
    void ensureNotNullOrBlank() {
        assertThat(Ensure.notNullOrBlank("test")).isEqualTo("test");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void ensureNotNullOrBlankWithMessage(String value) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Ensure.notNullOrBlank(value, "null"))
                .withMessage("String parameter must not be null or blank: null");
    }
}
