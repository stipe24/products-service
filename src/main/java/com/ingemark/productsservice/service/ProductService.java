package com.ingemark.productsservice.service;

import com.ingemark.productsservice.model.ProductEntity;
import com.ingemark.productsservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductEntity> get() {
        return productRepository.findAll();
    }

    public ProductEntity save(ProductEntity entity) {
        return productRepository.save(entity);
    }

    public ProductEntity update(int id, ProductEntity product) {
        var oldProduct = productRepository.findById(id);
        if (oldProduct.isPresent()) {
            //TODO: update name, price, description....
            return productRepository.save(oldProduct.get());
        }
        return null;
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

}