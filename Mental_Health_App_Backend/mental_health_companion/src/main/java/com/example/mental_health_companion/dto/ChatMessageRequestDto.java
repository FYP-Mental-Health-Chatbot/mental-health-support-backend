package com.example.mental_health_companion.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChatMessageRequestDto {

    private Long sessionId;
    private Long studentId;
    private Long senderId;
    private Long receiverId;
    private String messageText;
    private String messageSource;

}

