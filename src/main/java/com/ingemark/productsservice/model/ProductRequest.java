package com.ingemark.productsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String code;

    private String name;

    private float price;

    private Currency currency;

    private String description;

    private boolean isAvailable;

}