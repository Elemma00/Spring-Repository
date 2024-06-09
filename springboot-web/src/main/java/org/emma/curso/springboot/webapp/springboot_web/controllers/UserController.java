package org.emma.curso.springboot.webapp.springboot_web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.emma.curso.springboot.webapp.springboot_web.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * @Controller es una anotación en Spring Framework que indica que una clase 
 * es un controlador web. Esta anotación permite que Spring descubra 
 * automáticamente los componentes de controlador 
 * cuando escanea el código de la aplicación.

Un controlador en Spring MVC maneja las solicitudes entrantes de los usuarios 
y devuelve una respuesta. Por lo general, un controlador recibe la solicitud, 
realiza alguna lógica de negocio o manejo de datos, y luego genera una respuesta, 
que puede ser una vista (página HTML), un objeto de datos (en una API REST), 
un estado de error, etc.
 */
@Controller
public class UserController {

    // Aca se define la ruta que se va a usar para acceder a la pagina

    @GetMapping("/details")
    // Model es una interfaz en Spring que contiene los datos que se van a mostrar
    // en la vista.

    public String details(Model model) {
        User user = new User("Emmanuel", "Faúndez");
        user.setEmail("faundez76@gmail.com");
        model.addAttribute("user", user);
        model.addAttribute("title", "Hola Mundo Spring Boot");
        model.addAttribute("name", "Emmanuel");
        model.addAttribute("lastname", "Faúndez");
        return "details";
    }

    @GetMapping("/list")
    public String list(ModelMap model) {
        List<User> users = new ArrayList<>();
        model.addAttribute("users", users);
        model.addAttribute("title", "Listado de los usuarios");
        return "list";
    }
}
