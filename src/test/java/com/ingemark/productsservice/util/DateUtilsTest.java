package com.ingemark.productsservice.util;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void givenNull_whenFormatDate_thenReturnNull() {
        var result = DateUtils.format(null);
        assertNull(result);
    }

    @Test
    void givenInstant_whenFormatDate_thenReturnDate() {
        var result = DateUtils.format(Instant.ofEpochSecond(1649577600));
        assertEquals("2022-04-10", result);
    }

}