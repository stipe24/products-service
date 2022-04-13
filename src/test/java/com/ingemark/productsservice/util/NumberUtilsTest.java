package com.ingemark.productsservice.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberUtilsTest {

    @Test
    void givenNull_whenSafelyToDouble_thenReturnNull() {
        var result = NumberUtils.safelyToDouble(null);
        assertNull(result);
    }

    @Test
    void givenFloat_whenSafelyToDouble_thenReturnDouble() {
        var result = NumberUtils.safelyToDouble("4.71");
        assertEquals(4.71, result);
    }

    @Test
    void givenFloatWithComma_whenSafelyToDouble_thenReturnDouble() {
        var result = NumberUtils.safelyToDouble("4,71");
        assertEquals(4.71, result);
    }

    @Test
    void givenInvalidValue_whenSafelyToDouble_thenShouldThrowException() {
        assertThrows(NumberFormatException.class, () -> NumberUtils.safelyToDouble("a4.71"));
    }

    @Test
    void givenNull_whenRound_thenReturnNull() {
        var result = NumberUtils.round(null);
        assertNull(result);
    }

    @Test
    void givenNumber_whenRound_thenReturnRoundedDown() {
        var result = NumberUtils.round(4.713);
        assertEquals("4.71", result);
    }

    @Test
    void givenNumber_whenRound_thenReturnRoundedUp() {
        var result = NumberUtils.round(4.756);
        assertEquals("4.76", result);
    }

}