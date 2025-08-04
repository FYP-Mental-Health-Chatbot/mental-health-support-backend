package com.example.mental_health_companion.serviceImpl;

import com.example.mental_health_companion.domain.service.IChatbotService;
import com.example.mental_health_companion.dto.ChatResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatbotServiceImpl implements IChatbotService {

    private final LLMIntegrationService llmIntegrationService;

    @Override
    public ChatResponseDto getChatbotResponse(String studentMessage) {
        log.info("Processing student message: {}", studentMessage);

        // In this initial version, we simply pass the message to the LLM
        // In a later step, we'll add session management and risk detection here
        return llmIntegrationService.getResponseFromLLM(studentMessage);
    }
}
