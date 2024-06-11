package org.emma.curso.springboot.webapp.springboot_web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.emma.curso.springboot.webapp.springboot_web.models.User;
import org.emma.curso.springboot.webapp.springboot_web.models.dto.ParamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/** 
 * Controlador que recibe un parametro en la url
 * en este caso usamos el path variable
 * que es otro ejemplo de como recibir parametros desde la url
*/

@RestController
@RequestMapping("/api/var")
public class PathVariableController {


    //Anotación para inyectar valores desde el archivo application.properties
    @Value("${config.username}")
    private String username;
    @Value("${config.message}")
    private String message;
    @Value("${config.listOfValues}")
    private List<String> listOfValues;
    @Value("${config.code}")
    private Integer code;

    @Value("#{'${config.listOfValues}'.split(',')}")
    private List<String> valueList;

    @Value("#{${config.valuesMap}}")
    private Map<String,Object> valuesMap;

    @Value("#{${config.valuesMap}.key1}")
    private String key1;

    //podemos utilizar el environment para obtener valores
    //de las variables de entorno
    @Autowired
    private Environment environment;

    //utilizamos el path variable para recibir un parametro 
    //en la url, en este caso la variable message
    @GetMapping("/baz/{message}")
    public ParamDto baz(@PathVariable String message){
        ParamDto param = new ParamDto();
        param.setMessage(message);
        return param;
    }

    @GetMapping("/mix/{product}/{id}")
    public Map<String,Object> mixPathvar(@PathVariable String product, @PathVariable Long id){
        Map<String,Object> json = new HashMap<>();
        json.put("product", product);
        json.put("id", id);
        return json;
    }

    /**
     * Metodo que recibe un objeto de tipo User
     * y lo retorna con el nombre en mayusculas.
     * Este método es un ejemplo de como recibir un objeto
     * en el body de la petición. (POST)
     * @param user
     * @return user
     */
    @PostMapping("/create")
    public User create(@RequestBody User user){
        user.setName(user.getName().toUpperCase());
        return user;
    }

    @GetMapping("/values")
    public Map<String,Object> values(){
        Map<String,Object> json = new HashMap<>();
        json.put("username", username);
        json.put("message", message);
        json.put("listOfValues", listOfValues);
        json.put("code", code);
        json.put("valueList", valueList);
        json.put("valuesMap", valuesMap);
        json.put("key1", key1);
        json.put("message2", environment.getProperty("config.message"));
        json.put("code2", environment.getProperty("config.code"));
        return json;
    }
    
}
