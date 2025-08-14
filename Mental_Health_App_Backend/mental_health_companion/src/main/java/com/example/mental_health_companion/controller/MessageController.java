package com.example.mental_health_companion.controller;

import com.example.mental_health_companion.domain.entity.ChatSession;
import com.example.mental_health_companion.domain.entity.Message;
import com.example.mental_health_companion.dto.ChatMessageRequestDto;
import com.example.mental_health_companion.dto.ChatResponseDto;
import com.example.mental_health_companion.enums.ChatState;
import com.example.mental_health_companion.serviceImpl.ChatSessionServiceImpl;
import com.example.mental_health_companion.serviceImpl.ChatbotServiceImpl;
import com.example.mental_health_companion.serviceImpl.MessageToAdvisorServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Message", description = "Endpoints for interacting with the AI chatbot messages")
public class MessageController {

    @Autowired
    private ChatbotServiceImpl chatbotService;

    @Autowired
    private ChatSessionServiceImpl chatSessionService;

    @Autowired
    private MessageToAdvisorServiceImpl messageToAdvisorService;

    @PostMapping("/message")
    public ResponseEntity<ChatResponseDto> handleStudentMessage(@RequestBody ChatMessageRequestDto messageDto) {
        Long sessionId = messageDto.getSessionId();

        if (sessionId == null) {
            // This is the first message of a new session.
            // Create a new ChatSession and save the initial message.
            ChatSession newSession = chatSessionService.createSession(messageDto.getSenderId());

            ChatResponseDto chatbotResponse = chatbotService.sendMessageToChatbot(messageDto.getStudentId(), messageDto.getMessageText());
            return ResponseEntity.ok(chatbotResponse);

        }

        else {
            // Retrieve the full ChatSession entity using the session ID
            ChatSession chatSession = chatbotService.getChatSession(sessionId);

            // Check if the chat is currently with an advisor
            if (chatSession.getChatState() == ChatState.ADVISOR) {
                // Route message directly to the advisor's app via push notification
                messageToAdvisorService.sendMessageToAdvisor(messageDto.getReceiverId(), messageDto.getMessageText());
                // Return an empty response, as the advisor's reply will be a separate push notification
                return ResponseEntity.ok(new ChatResponseDto("Advisor is active. Waiting for their reply.", "advisor"));
            } else {
                // Route message to the Python chatbot backend
                ChatResponseDto chatbotResponse = chatbotService.sendMessageToChatbot(messageDto.getStudentId(), messageDto.getMessageText());
                return ResponseEntity.ok(chatbotResponse);
            }
        }

    }
}