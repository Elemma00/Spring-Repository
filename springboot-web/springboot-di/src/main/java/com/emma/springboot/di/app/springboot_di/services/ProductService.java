package com.emma.springboot.di.app.springboot_di.services;

import java.util.List;

import com.emma.springboot.di.app.springboot_di.models.Product;
import com.emma.springboot.di.app.springboot_di.repositories.ProductRepository;

public class ProductService {

    private ProductRepository repository = new ProductRepository();

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id);
    }


}
