package com.ingemark.productsservice.service;

import com.ingemark.productsservice.model.*;
import com.ingemark.productsservice.repository.ProductRepository;
import com.ingemark.productsservice.util.CurrencyExchange;
import com.ingemark.productsservice.util.CurrencyExchangeResolver;
import com.ingemark.productsservice.util.NumberUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CurrencyExchange currencyExchange;

    public List<Product> get() {
        CurrencyExchanges currencyExchanges = (CurrencyExchanges) currencyExchange.getCurrencyExchange();
        return productRepository.findAll().stream().map(p -> map(p, currencyExchanges)).collect(Collectors.toList());
    }

    public ProductEntity save(ProductRequest product) {
        return productRepository.save(from(product));
    }

    public ProductEntity update(int id, ProductRequest product) {
        var oldProduct = productRepository.findById(id);
        if (oldProduct.isPresent()) {
            oldProduct.get().setCode(product.getCode());
            oldProduct.get().setName(product.getName());
            oldProduct.get().setPrice(product.getPrice());
            oldProduct.get().setCurrency(product.getCurrency());
            oldProduct.get().setDescription(product.getDescription());
            oldProduct.get().setAvailable(product.isAvailable());
            return productRepository.save(oldProduct.get());
        }
        return null;
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

    private Product map(ProductEntity productEntity, CurrencyExchanges currencyExchanges) {
        return Product.builder()
                .code(productEntity.getCode())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .isAvailable(productEntity.isAvailable())
                .priceHrk(NumberUtils.round(productEntity.getPrice()))
                .priceEur(CurrencyExchangeResolver.resolvePriceInEur(productEntity.getPrice(), currencyExchanges))
                .build();
    }

    private ProductEntity from(ProductRequest product) {
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