package com.ensat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureApiController {
    @GetMapping("/secure-hello")
    public String getHello(){
        return "Hello, Secure World!";
    }
}
