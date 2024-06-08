package org.emma.curso.springboot.webapp.springboot_web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.emma.curso.springboot.webapp.springboot_web.models.User;
import org.emma.curso.springboot.webapp.springboot_web.models.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController es una anotación en Spring MVC que simplifica la creación de
// controladores REST. Esta anotación incluye las anotaciones @Controller y @ResponseBody.
@RestController
@RequestMapping("/api")
public class UserRestController {

    // La anotacion @GetMapping es una anotación compuesta que actúa como un atajo
    // para
    // @RequestMapping(method = RequestMethod.GET).
    // En este caso: @RequestMapping(value = "/details", method = RequestMethod.GET)
    @GetMapping("/details")
    public UserDto details() {
        UserDto userDto = new UserDto();
        User user = new User("Emmanuel", "Faúndez");
        userDto.setUser(user);
        userDto.setTitle("Hola Mundo Spring Boot");
        return userDto;
    }

    @GetMapping("/details-map")
    public Map<String, Object> detailsMap() {
        User user = new User("Emmanuel", "Faúndez");
        Map<String, Object> body = new HashMap<>();
        body.put("title", "Hola Mundo Spring Boot");
        body.put("user", user);
        return body;
    }
}
