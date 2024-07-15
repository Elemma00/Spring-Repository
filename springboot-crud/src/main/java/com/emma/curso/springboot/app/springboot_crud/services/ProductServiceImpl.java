package com.emma.curso.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emma.curso.springboot.app.springboot_crud.entities.Product;
import com.emma.curso.springboot.app.springboot_crud.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
    
    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productOptional = findById(id);
        if (productOptional.isPresent()){
            Product productDB = productOptional.orElseThrow();
            productDB.setSku(product.getSku());
            productDB.setName(product.getName());
            productDB.setPrice(product.getPrice());
            productDB.setDescription(product.getDescription());
            return Optional.of(productRepository.save(productDB));
        }
        return productOptional;

    }


    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> productDB = findById(id);
        productDB.ifPresent(productRepository::delete);
        return productDB;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsBySku(String sku) {
       return productRepository.existsBySku(sku);
    }

   


}
