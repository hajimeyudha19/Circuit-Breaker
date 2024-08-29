package com.ensat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * Homepage controller.
 */
@RestController
@Slf4j
public class IndexController {

    @GetMapping("/index")
    String index() {
        log.info("you are accessing index");
        return "Hello World!";
    }

}
