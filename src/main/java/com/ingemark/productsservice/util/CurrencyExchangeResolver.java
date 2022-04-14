package com.ingemark.productsservice.util;

import com.ingemark.productsservice.model.Currency;
import com.ingemark.productsservice.model.CurrencyExchanges;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.ingemark.productsservice.util.NumberUtils.safelyToDouble;
import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CurrencyExchangeResolver {

    public static String resolveEurPrice(Double priceHrk, CurrencyExchanges currencyExchanges) {
        if (isNull(priceHrk) || isNull(currencyExchanges)) {
            return null;
        }
        var rate = currencyExchanges.getCurrencyExchanges().get(Currency.EUR).getMiddleRate();
        if (isNull(rate)) {
            return null;
        }
        return NumberUtils.round(priceHrk / safelyToDouble(rate));
    }

}