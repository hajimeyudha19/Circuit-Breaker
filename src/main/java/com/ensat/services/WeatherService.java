package com.ensat.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final RestTemplate restTemplate;

    @Value("${openweather.api.key}")
    private String apiKey;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "weatherService", fallbackMethod = "weatherFallback")
    public String getWeatherForJakarta() {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("q", "Jakarta")
                .queryParam("appid", apiKey)
                .toUriString();

        logger.info("Calling OpenWeatherMap API with URL: {}", url);

        try {
            String response = restTemplate.getForObject(url, String.class);
            logger.info("Received response: {}", response);
            return response;
        } catch (Exception ex) {
            logger.error("Error occurred while calling OpenWeatherMap API: {}", ex.getMessage());
            throw ex;
        }
    }

    public String weatherFallback(Throwable t) {
        logger.error("Circuit breaker triggered, fallback method called. Reason: {}", t.getMessage());
        return "Weather service is currently unavailable. Please try again later.";
    }
}




