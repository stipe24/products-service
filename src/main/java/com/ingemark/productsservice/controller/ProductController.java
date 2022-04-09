package com.ingemark.productsservice.controller;

import com.ingemark.productsservice.model.ProductEntity;
import com.ingemark.productsservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductEntity> getProducts() {
        return productService.get();
    }

    @PostMapping
    public ProductEntity create(ProductEntity product) {
        return productService.save(product);
    }

    @PatchMapping("/{id}")
    public ProductEntity update(@PathVariable int id, ProductEntity product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }

}