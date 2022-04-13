package com.ingemark.productsservice.util;

import com.ingemark.productsservice.model.Currency;
import com.ingemark.productsservice.model.CurrencyExchanges;
import com.ingemark.productsservice.model.HnbV2Model;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyExchangeResolverTest {

    private static final String EUR_MIDDLE_RATE = "7.5";

    @Test
    void givenNull_whenResolveEurPrice_thenReturnNull() {
        var result = CurrencyExchangeResolver.resolveEurPrice(null, null);
        assertNull(result);
    }

    @Test
    void givenNullHrkPrice_whenResolveEurPrice_thenReturnNull() {
        var result = CurrencyExchangeResolver.resolveEurPrice(null, givenCurrencyExchanges());
        assertNull(result);
    }

    @Test
    void givenNullCurrencyExchanges_whenResolveEurPrice_thenReturnNull() {
        var result = CurrencyExchangeResolver.resolveEurPrice(100.5, null);
        assertNull(result);
    }

    @Test
    void givenValidValues_whenResolveEurPrice_thenReturnPrice() {
        var result = CurrencyExchangeResolver.resolveEurPrice(100.5, givenCurrencyExchanges());
        assertEquals("13.4", result);
    }


    private CurrencyExchanges givenCurrencyExchanges() {
        Map<Currency, HnbV2Model> currencyExchangesMap = new HashMap<>();
        currencyExchangesMap.put(Currency.EUR, new HnbV2Model(EUR_MIDDLE_RATE));

        return new CurrencyExchanges(currencyExchangesMap);
    }

}