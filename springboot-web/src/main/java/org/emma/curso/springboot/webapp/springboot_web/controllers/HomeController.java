package org.emma.curso.springboot.webapp.springboot_web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({ "", "/", "/home" })
    public String home() {
        return "forward:/list";
        // return "redirect:/list";
        //La diferencia de forward y redirect es que 
        //forward es una redirección interna, por lo cual
        //el navegador no cambia la url, mientras que
        //redirect es una redirección externa además de que los 
        //datos de la petición se pierden.
    }
}
