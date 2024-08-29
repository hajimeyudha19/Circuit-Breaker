package com.ensat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensat.services.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/jakarta")
    public ResponseEntity<String> getWeatherForJakarta() {
        String weather = weatherService.getWeatherForJakarta();
        return ResponseEntity.ok(weather);
    }
}