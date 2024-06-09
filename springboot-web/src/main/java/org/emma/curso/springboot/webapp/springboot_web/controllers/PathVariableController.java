package org.emma.curso.springboot.webapp.springboot_web.controllers;

import org.emma.curso.springboot.webapp.springboot_web.models.dto.ParamDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * Controlador que recibe un parametro en la url
 * en este caso usamos el path variable
 * que es otro ejemplo de como recibir parametros desde la url
*/

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    //utilizamos el path variable para recibir un parametro 
    //en la url, en este caso la variable message
    @GetMapping("/baz/{message}")
    public ParamDto baz(@PathVariable String message){
        ParamDto param = new ParamDto();
        return param;
    }
}
