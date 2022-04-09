package com.ingemark.productsservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class NumberUtils {

    public static Float safelyToFloat(String value) {
        if (Objects.isNull(value)) {
            return null;
        }
        try {
            return Float.valueOf(value.replace(",", "."));
        } catch (NumberFormatException e) {
            log.error("Error occurred while parsing " + value);
            return null;
        }
    }

}