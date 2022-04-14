package com.ingemark.productsservice.controller;

import com.ingemark.productsservice.model.Product;
import com.ingemark.productsservice.model.ProductRequest;
import com.ingemark.productsservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> get() {
        return productService.get();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable int id) {
        return productService.get(id);
    }

    @PostMapping
    public Product create(@Valid @RequestBody ProductRequest product) {
        return productService.save(product);
    }

    @PatchMapping("/{id}")
    public Product update(@PathVariable int id, @Valid @RequestBody ProductRequest product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }

}