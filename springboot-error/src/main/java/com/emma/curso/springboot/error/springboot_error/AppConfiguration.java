package com.emma.curso.springboot.error.springboot_error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.emma.curso.springboot.error.springboot_error.models.domain.User;

@Configuration
public class AppConfiguration {

    @Bean
    public List<User> users() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Emma", "Garcia"));
        users.add(new User(2L, "Juan", "Perez"));
        users.add(new User(3L, "Pedro", "Gomez"));
        users.add(new User(4L, "Maria", "Lopez"));
        users.add(new User(5L, "Laura", "Gonzalez"));
        return users;
    }

}
