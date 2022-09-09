package com.jackbourner.reactthymeleaf.controller;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class ContactResponse {

    Status status;
    String message;

    public enum Status {

        SUCCESS("success"),
        WARNING("warning"),
        DANGER("danger");

        private final String status;

        Status(String status) {
            this.status = status;
        }

        @JsonValue
        public String getStatus() {
            return status;
        }

    }

}
