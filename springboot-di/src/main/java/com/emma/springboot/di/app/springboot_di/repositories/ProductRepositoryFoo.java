package com.emma.springboot.di.app.springboot_di.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.emma.springboot.di.app.springboot_di.models.Product;

@Repository("productListFoo")
public class ProductRepositoryFoo implements ProductRepository{

    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L, "Memoria Giga 32", 300L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(id,"Memoria Giga 32", 300L);
    }

}
