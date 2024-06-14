package com.emma.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.emma.springboot.di.app.springboot_di.models.Product;
import com.emma.springboot.di.app.springboot_di.repositories.ProductRepository;

@Service
@Primary
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private Environment environment;
    private ProductRepository repository;

    public ProductServiceImpl(@Qualifier("productRepositoryJson") ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        Double tax = environment.getProperty("config.tax.price", Double.class);
        return repository.findAll().stream().map(p->{
            Double priceTax = p.getPrice() * tax;
            // Product newProd = new Product(p.getId(),p.getName(),priceTax.longValue());
            Product newProd = (Product) p.clone();
            newProd.setPrice(priceTax.longValue());
            return newProd;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }


    

}
