package com.example.mental_health_companion.domain.service;

import com.example.mental_health_companion.domain.entity.ChatSession;

public interface IChatbotService {
    ChatSession getChatSession(Long sessionId);

    ChatSession getChatSessionByStudentId(Long studentId);
//    ChatResponseDto getChatbotResponse(String studentMessage);
}
