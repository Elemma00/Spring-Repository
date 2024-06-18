package com.emma.curso.springboot.error.springboot_error.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emma.curso.springboot.error.springboot_error.models.domain.User;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private List<User> users;
    
    @Override
    public List<User> findAll() {
        return users;
    }

    @PostConstruct
    public void init(){
        System.out.println("Inicializando los usuarios");
    }

    @PreDestroy
    public void destroid(){
        System.out.println("Destruyendo los usuarios");
    }
    

    @Override
    public Optional<User> findByid(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }
}
