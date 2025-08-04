package com.example.mental_health_companion.domain.service;

import com.example.mental_health_companion.dto.ChatResponseDto;
import org.springframework.stereotype.Service;

public interface IChatbotService {
    ChatResponseDto getChatbotResponse(String studentMessage);
}
