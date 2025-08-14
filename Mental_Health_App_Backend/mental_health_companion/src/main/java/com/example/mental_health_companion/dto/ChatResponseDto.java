package com.example.mental_health_companion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatResponseDto {
    private String response;
    private String messageSource;
}

