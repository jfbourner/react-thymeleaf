package com.jackbourner.reactthymeleaf.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "google.recaptcha.key")
public record CaptchaSettings(
        String url,
        String site,
        String secret,
        float threshold
){}

