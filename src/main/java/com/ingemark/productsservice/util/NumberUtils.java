package com.ingemark.productsservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class NumberUtils {

    private static final String DECIMAL_FORMAT = "#.##";

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

    public static String round(Float number) {
        if (Objects.isNull(number)) {
            return null;
        }
        DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT);
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(number);
    }

}