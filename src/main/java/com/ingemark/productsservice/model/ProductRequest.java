package com.ingemark.productsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotNull(message = "Code cannot be null")
    @Size(min = 10, max = 10)
    private String code;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Price cannot be null")
    @PositiveOrZero(message = "Price must be positive or zero")
    private Double price;

    private Currency currency = Currency.HRK;

    private String description;

    private boolean isAvailable = true;

    public static ProductEntity toEntity(ProductRequest product) {
        return ProductEntity.builder()
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .description(product.getDescription())
                .isAvailable(product.isAvailable())
                .build();
    }

}