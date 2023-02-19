package com.jackbourner.reactthymeleaf.model;

public record RecaptchaResponse(
        Boolean success,
        float score,
        String action,
        String challenge_ts,
        String hostname

) {
}
