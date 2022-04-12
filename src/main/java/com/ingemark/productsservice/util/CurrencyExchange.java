package com.ingemark.productsservice.util;

import com.ingemark.productsservice.model.Currency;
import com.ingemark.productsservice.model.HnbModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@AllArgsConstructor
public class CurrencyExchange {

    private static final String HNB_URL = "https://api.hnb.hr/tecajn/v2?valuta=";

    public HnbModel getCurrencyExchange(Currency currency) {

        RestTemplate restTemplate = new RestTemplate();
        var hnbModel = restTemplate.getForObject(HNB_URL + currency.name(), HnbModel[].class);

        if (Objects.nonNull(hnbModel)) {
            return hnbModel[0];
        }
        return null;
    }

}