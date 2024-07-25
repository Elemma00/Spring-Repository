package com.emma.springboot.client.client_app.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emma.springboot.client.client_app.models.Message;

@RestController
public class AppController {

    @GetMapping("/list")
    public List<Message> list(){
        return Collections.singletonList(new Message("Test list"));
    }

    @PostMapping("/create")
    public Message create(@RequestBody Message message){
        System.out.println("Mensaje guardado: " + message.getText());
        return message;
    }

    @GetMapping("/authorized")
    public Map<String, String> authorized (@RequestParam String code){
        return Collections.singletonMap("code", code);
    }
}
