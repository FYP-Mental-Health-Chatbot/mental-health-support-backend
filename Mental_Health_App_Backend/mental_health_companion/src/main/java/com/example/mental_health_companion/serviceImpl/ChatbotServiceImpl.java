package com.example.mental_health_companion.serviceImpl;

import com.example.mental_health_companion.domain.entity.ChatSession;
import com.example.mental_health_companion.domain.entity.Message;
import com.example.mental_health_companion.domain.entity.User;
import com.example.mental_health_companion.domain.repository.ChatRepository;
import com.example.mental_health_companion.domain.repository.MessageRepository;
import com.example.mental_health_companion.domain.repository.UserRepository;
import com.example.mental_health_companion.domain.service.IChatbotService;
import com.example.mental_health_companion.dto.ChatMessageRequestDto;
import com.example.mental_health_companion.dto.ChatResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatbotServiceImpl implements IChatbotService {


    @Value("${python.chatbot.url}")
    private String pythonChatbotUrl;

    @Autowired
    private MessageRepository chatMessageRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public ChatResponseDto sendMessageToChatbot(Long studentId, String message) {
        // Log student's message
        saveChatMessage(studentId, 123L, message, "student");

        String url = pythonChatbotUrl + "/chat";
        ChatMessageRequestDto requestDto = new ChatMessageRequestDto();
        requestDto.setStudentId(studentId);
        requestDto.setMessageText(message);
//        requestDto.setChatHistory(Collections.emptyList()); // Simplified; real app would fetch history

        ChatResponseDto responseDto = restTemplate.postForObject(url, requestDto, ChatResponseDto.class);

        if (responseDto != null) {
            // Log chatbot's response
            saveChatMessage(studentId, 1234L, responseDto.getResponse(), "bot");
        }

        return responseDto;
    }

    private void saveChatMessage(Long studentId, Long senderId, String message, String source) {
        Message chatMessage = new Message();
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("Sender not found with ID: " + senderId));

        chatMessage.setStudentId(studentId);
        chatMessage.setSender(sender);
        chatMessage.setMessageText(message);
        chatMessage.setSentAt(LocalDateTime.now());
        chatMessage.setMessageSource(source);
        chatMessageRepository.save(chatMessage);
    }

    @Override
    public ChatSession getChatSession(Long sessionId) {
        ChatSession chat = chatRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("chat not found with session id: " + sessionId));

        return chat;
    }

    @Override
    public ChatSession getChatSessionByStudentId(Long studentId) {
        ChatSession chatSession = chatRepository.findByStudent_StudentDetails_StudentId(studentId)
                .orElseThrow(() -> new IllegalArgumentException("chat not found with session id: " + studentId));
        return null;
    }


//    private final LLMIntegrationService llmIntegrationService;
//
//    @Override
//    public ChatResponseDto getChatbotResponse(String studentMessage) {
//        log.info("Processing student message: {}", studentMessage);
//
//        // In this initial version, we simply pass the message to the LLM
//        // In a later step, we'll add session management and risk detection here
//        return llmIntegrationService.getResponseFromLLM(studentMessage);
//    }
}
