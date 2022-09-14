package com.jackbourner.reactthymeleaf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SuppressWarnings("unused")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/warzone").setViewName("warzone");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin").setViewName("admin");
    }
}