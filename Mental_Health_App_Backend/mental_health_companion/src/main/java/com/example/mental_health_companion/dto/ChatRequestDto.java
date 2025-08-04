package com.example.mental_health_companion.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatRequestDto {
    @NotBlank
    private String message;
}

