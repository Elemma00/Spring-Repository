package org.emma.curso.springboot.webapp.springboot_web.controllers;

import org.emma.curso.springboot.webapp.springboot_web.models.dto.ParamDto;
import org.emma.curso.springboot.webapp.springboot_web.models.dto.ParamDtoMix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


/**
 * Clase que muestra el uso de parametros en una peticion
 * en esta clase hay 2 ejemplos de como recibir parametros
 *  - utilizando @RequestParam
 *  - utilizando HttpServletRequest
 */

@RestController
@RequestMapping("/api/params")
public class RequestParamsController {

    @GetMapping("/foo")
    public ParamDto foo(@RequestParam(required = false, defaultValue = "Hola que tal")String message){
        ParamDto param = new ParamDto();
        param.setMessage(message);
        return param;
    }

    @GetMapping("/bar")
    public ParamDtoMix bar(@RequestParam(required = false) String message, @RequestParam(required = false) Integer code){
        ParamDtoMix params = new ParamDtoMix();
        params.setMessage(message);
        params.setCode(code);
        return params;
    }

    @GetMapping("/request")
    public ParamDtoMix request(HttpServletRequest request){
        Integer code = 0;
        try {
            code = Integer.parseInt(request.getParameter("code"));
        } catch (NumberFormatException e) {     
        }
        ParamDtoMix params = new ParamDtoMix();
        params.setCode(code);
        params.setMessage(request.getParameter("message"));
        return params;
    }

}
