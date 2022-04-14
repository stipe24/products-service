package com.ingemark.productsservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HnbV2Model implements Serializable {

    @JsonProperty("valuta")
    private String currency;

    @JsonProperty("kupovni_tecaj")
    private String buyingRate;

    @JsonProperty("srednji_tecaj")
    private String middleRate;

    @JsonProperty("prodajni_tecaj")
    private String sellingRate;

    public HnbV2Model(String middleRate) {
        this.middleRate = middleRate;
    }

}