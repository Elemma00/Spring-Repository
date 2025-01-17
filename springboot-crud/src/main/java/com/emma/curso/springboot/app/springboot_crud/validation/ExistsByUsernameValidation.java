package com.emma.curso.springboot.app.springboot_crud.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emma.curso.springboot.app.springboot_crud.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String>{

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(userService == null) return true;

        return !userService.existsByUsername(username);
    }

   

}
