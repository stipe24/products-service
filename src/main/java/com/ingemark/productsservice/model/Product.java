package com.ingemark.productsservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private String code;

    private String name;

    private String description;

    private boolean isAvailable;

    private Float priceHrk;

    private Float priceEur;

}