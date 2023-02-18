package com.jackbourner.reactthymeleaf.model;

public record RecaptchaResponse(
        Boolean success,
        String challenge_ts,
        String hostname,
        float score,
        String action) {
}
