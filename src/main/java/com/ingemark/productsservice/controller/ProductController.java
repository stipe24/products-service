package com.ingemark.productsservice.controller;

import com.ingemark.productsservice.model.Product;
import com.ingemark.productsservice.model.ProductEntity;
import com.ingemark.productsservice.model.ProductRequest;
import com.ingemark.productsservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.get(), HttpStatus.OK);
    }

    @PostMapping
    public ProductEntity create(ProductRequest product) {
        return productService.save(product);
    }

    @PatchMapping("/{id}")
    public ProductEntity update(@PathVariable int id, ProductRequest product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }

}