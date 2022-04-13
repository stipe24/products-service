package com.ingemark.productsservice.util;

import com.ingemark.productsservice.model.Currency;
import com.ingemark.productsservice.model.CurrencyExchanges;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CurrencyExchangeResolver {

    public static String resolvePriceInEur(float priceInHrk, CurrencyExchanges currencyExchanges) {
        var rate = currencyExchanges.getCurrencyExchanges().get(Currency.EUR).getMiddleRate();
        if (Objects.isNull(rate)) {
            return null;
        }
        return NumberUtils.round(priceInHrk / NumberUtils.safelyToFloat(rate));
    }
}