package com.emma.curso.springboot.error.springboot_error.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emma.curso.springboot.error.springboot_error.exceptions.UserNotFoundException;
import com.emma.curso.springboot.error.springboot_error.models.domain.User;
import com.emma.curso.springboot.error.springboot_error.services.UserService;


@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService service;

    @GetMapping
    public String index() {
        // int a = 1/0;
        int value = Integer.parseInt("10x");
        System.out.println(value);
        return "ok 200";
    }

    @GetMapping("/show/{id}")
    public User show(@PathVariable(name="id") Long id){
        Optional<User> user = service.findByid(id);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new UserNotFoundException("Error el usuario no existe!");
        }
    }

  
}
