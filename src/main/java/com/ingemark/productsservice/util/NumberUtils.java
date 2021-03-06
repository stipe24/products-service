package com.ingemark.productsservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static java.lang.Double.valueOf;
import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class NumberUtils {

    private static final String DECIMAL_FORMAT = "#.##";

    public static Double safelyToDouble(String value) {
        if (isNull(value)) {
            return null;
        }
        try {
            return valueOf(value.replace(",", "."));
        } catch (NumberFormatException e) {
            log.error("Error occurred while parsing " + value);
            throw e;
        }
    }

    public static String round(Double number) {
        if (isNull(number)) {
            return null;
        }
        DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT);
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(number);
    }

}