package com.emma.springboot.di.app.springboot_di.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emma.springboot.di.app.springboot_di.models.Product;

@RestController
@RequestMapping("/api")
public class SomeController {

    public List<Product> list(){}

    @GetMapping("/{id}")
    public Product show(@PathVariable Long id){
        
    }
}
