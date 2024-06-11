package com.emma.springboot.di.app.springboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import com.emma.springboot.di.app.springboot_di.models.Product;

public class ProductRepository {

    private List<Product> data;

    public ProductRepository() {
        this.data = Arrays.asList(
                new Product(1L, "Memoria Corsair 32", 300L),
                new Product(2L, "Intel Core I9", 1000L),
                new Product(3L, "Motherboard Asus", 500L),
                new Product(4L, "Nvidia 4090ti RTX", 2000L));
    }

    public List<Product> findAll(){
        return data;
    }

    public Product findById(Long id){
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElseGet(null);
    }

}
