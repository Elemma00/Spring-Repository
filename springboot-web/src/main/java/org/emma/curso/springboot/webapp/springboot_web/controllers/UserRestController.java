package org.emma.curso.springboot.webapp.springboot_web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.emma.curso.springboot.webapp.springboot_web.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController es una anotación en Spring MVC que simplifica la creación de
// controladores REST. Esta anotación incluye las anotaciones @Controller y @ResponseBody.
@RestController
@RequestMapping("/api")
public class UserRestController {

    @GetMapping("/details")
    // La anotacion @GetMapping es una anotación compuesta que actúa como un atajo para
    // @RequestMapping(method = RequestMethod.GET).
    // En este caso: @RequestMapping(value = "/details", method = RequestMethod.GET)
    
    public Map<String, Object> details() {
        Map<String, Object> body = new HashMap<>();
        User user = new User("Emmanuel", "Faúndez");
        body.put("title", "Hola Mundo Spring Boot");
        body.put("user", user);
        return body;
    }
}
