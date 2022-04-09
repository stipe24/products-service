package com.ingemark.productsservice.util;

import com.ingemark.productsservice.model.Currency;
import com.ingemark.productsservice.model.ProductEntity;
import com.ingemark.productsservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        populateData();
    }

    private void populateData() {

        ProductEntity iPhone = new ProductEntity();
        iPhone.setId(1);
        iPhone.setCode("1111111111");
        iPhone.setName("iPhone 13");
        iPhone.setPrice(11000);
        iPhone.setCurrency(Currency.HRK);
        iPhone.setAvailable(true);

        ProductEntity audi = new ProductEntity();
        audi.setId(2);
        audi.setCode("3333333333");
        audi.setName("Audi A4");
        audi.setDescription("2019 2.0");
        audi.setPrice(230000);
        audi.setCurrency(Currency.HRK);
        audi.setAvailable(true);

        ProductEntity shirt = new ProductEntity();
        shirt.setId(3);
        shirt.setCode("9999999999");
        shirt.setName("New Shirt");
        shirt.setPrice(105.60f);
        shirt.setCurrency(Currency.HRK);
        shirt.setAvailable(true);

        productRepository.saveAll(List.of(iPhone, audi, shirt));

    }

}