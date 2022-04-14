package com.ingemark.productsservice.service;

import com.ingemark.productsservice.model.CurrencyExchanges;
import com.ingemark.productsservice.model.Product;
import com.ingemark.productsservice.model.ProductEntity;
import com.ingemark.productsservice.model.ProductRequest;
import com.ingemark.productsservice.repository.ProductRepository;
import com.ingemark.productsservice.util.CurrencyExchange;
import com.ingemark.productsservice.util.CurrencyExchangeResolver;
import com.ingemark.productsservice.util.NumberUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ingemark.productsservice.model.ProductRequest.toEntity;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CurrencyExchange currencyExchange;

    public List<Product> get() {
        CurrencyExchanges currencyExchanges = (CurrencyExchanges) currencyExchange.getCurrencyExchange();
        return productRepository.findAll().stream()
                .map(product -> map(product, currencyExchanges)).collect(Collectors.toList());
    }

    public Product get(int id) {
        var product = productRepository.findById(id);
        return product.map(p ->
                map(p, (CurrencyExchanges) currencyExchange.getCurrencyExchange())
        ).orElse(null);
    }

    public Product save(ProductRequest product) {
        return map(
                productRepository.save(toEntity(product)),
                (CurrencyExchanges) currencyExchange.getCurrencyExchange()
        );
    }

    public Product update(int id, ProductRequest product) {
        var oldProduct = productRepository.findById(id);
        if (oldProduct.isPresent()) {
            oldProduct.get().setCode(product.getCode());
            oldProduct.get().setName(product.getName());
            oldProduct.get().setPrice(product.getPrice());
            oldProduct.get().setCurrency(product.getCurrency());
            oldProduct.get().setDescription(product.getDescription());
            oldProduct.get().setAvailable(product.isAvailable());
            return map(
                    productRepository.save(oldProduct.get()),
                    (CurrencyExchanges) currencyExchange.getCurrencyExchange()
            );
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
                .priceEur(CurrencyExchangeResolver.resolveEurPrice(productEntity.getPrice(), currencyExchanges))
                .build();
    }

}