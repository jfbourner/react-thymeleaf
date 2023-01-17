package com.jackbourner.reactthymeleaf.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class WarzoneRequest {

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 30)
    String username;

    @NotNull
    @NotEmpty
    String platform;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    LocalDateTime dateFrom;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    LocalDateTime dateTo;
}
