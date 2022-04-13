package com.ingemark.productsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchanges implements Serializable {

    private Map<Currency, HnbV2Model> currencyExchanges;

}