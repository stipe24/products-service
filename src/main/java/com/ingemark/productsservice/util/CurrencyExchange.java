package com.ingemark.productsservice.util;

import com.ingemark.productsservice.model.Currency;
import com.ingemark.productsservice.model.CurrencyExchanges;
import com.ingemark.productsservice.model.HnbV2Model;
import com.ingemark.productsservice.service.RedisService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@AllArgsConstructor
public class CurrencyExchange {

    private final RedisService redisService;

    private static final String HNB_URL = "https://api.hnb.hr/tecajn/v2?valuta=";
    private static final String CURRENCY_EXCHANGE_KEY = "currency-exchange-key-";

    public Object getCurrencyExchange() {

        var currentDate = DateUtils.format(Instant.now());
        var cachedCurrencyExchanges = redisService.get(CURRENCY_EXCHANGE_KEY + currentDate);

        if (Objects.nonNull(cachedCurrencyExchanges)) {
            return cachedCurrencyExchanges;
        }
        var currencyExchanges = fetchCurrencyExchanges();
        redisService.save(CURRENCY_EXCHANGE_KEY + currentDate, currencyExchanges);
        return currencyExchanges;
    }

    private CurrencyExchanges fetchCurrencyExchanges() {
        Map<Currency, HnbV2Model> currencyExchanges = new HashMap<>();
        for (Currency currency : Currency.values()) {
            RestTemplate restTemplate = new RestTemplate();
            var hnbModel = restTemplate.getForObject(HNB_URL + currency.name(), HnbV2Model[].class);
            if (Objects.nonNull(hnbModel) && hnbModel.length != 0) {
                currencyExchanges.put(currency, hnbModel[0]);
            }
        }
        return new CurrencyExchanges(currencyExchanges);
    }

}