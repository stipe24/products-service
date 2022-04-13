package com.ingemark.productsservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String format(Instant date) {
        if (Objects.isNull(date)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT).withZone(ZoneOffset.UTC);
        return formatter.format(date);
    }

}