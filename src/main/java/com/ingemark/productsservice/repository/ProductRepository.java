package com.ingemark.productsservice.repository;

import com.ingemark.productsservice.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}