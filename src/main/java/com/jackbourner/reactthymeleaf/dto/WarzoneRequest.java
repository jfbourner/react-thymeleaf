package com.jackbourner.reactthymeleaf.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class WarzoneRequest {

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 30)
    private String username;

    @NotNull
    @NotEmpty
    private String platform;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateFrom;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateTo;
}
