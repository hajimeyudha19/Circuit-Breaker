package com.ensat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ensat.services.RedirectInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public RedirectInterceptor redirectInterceptor() {
        return new RedirectInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(redirectInterceptor());
    }
}

