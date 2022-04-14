package com.ingemark.productsservice.util;

import com.ingemark.productsservice.model.Currency;
import com.ingemark.productsservice.model.CurrencyExchanges;
import com.ingemark.productsservice.model.HnbV2Model;
import com.ingemark.productsservice.service.RedisService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.ingemark.productsservice.util.DateUtils.format;
import static java.util.Objects.nonNull;

@Component
@AllArgsConstructor
@Slf4j
public class CurrencyExchange {

    private final RedisService redisService;
    private final Environment environment;

    private static final String HNB_URL = "https://api.hnb.hr/tecajn/v2?valuta=";
    private static final String CURRENCY_EXCHANGE_KEY = "currency-exchange-key-";
    private static final String CURRENCY_MIDDLE_RATE_PROPERTY = "application.currency.:getCurrency.middle";

    public Object getCurrencyExchange() {

        var currentDate = format(Instant.now());
        try {
            var cachedCurrencyExchanges = redisService.get(CURRENCY_EXCHANGE_KEY + currentDate);
            if (nonNull(cachedCurrencyExchanges)) {
                return cachedCurrencyExchanges;
            }
            var currencyExchanges = fetchCurrencyExchanges();
            redisService.save(CURRENCY_EXCHANGE_KEY + currentDate, currencyExchanges);
            return currencyExchanges;
        } catch (Exception e) {
            log.error(e.getMessage());
            return getDefaultExchanges();
        }
    }

    private CurrencyExchanges fetchCurrencyExchanges() {
        Map<Currency, HnbV2Model> currencyExchanges = new HashMap<>();
        for (Currency currency : Currency.values()) {
            RestTemplate restTemplate = new RestTemplate();
            var hnbModel = restTemplate.getForObject(HNB_URL + currency.name(), HnbV2Model[].class);
            if (nonNull(hnbModel) && hnbModel.length != 0) {
                currencyExchanges.put(currency, hnbModel[0]);
            }
        }
        return new CurrencyExchanges(currencyExchanges);
    }

    private Object getDefaultExchanges() {
        Map<Currency, HnbV2Model> currencyExchanges = new HashMap<>();
        for (Currency currency : Currency.values()) {
            currencyExchanges.put(
                    currency,
                    HnbV2Model.builder()
                            .middleRate(environment.getProperty(CURRENCY_MIDDLE_RATE_PROPERTY.replace(":getCurrency", currency.name().toLowerCase())))
                            .build()
            );
        }
        return new CurrencyExchanges(currencyExchanges);
    }

}