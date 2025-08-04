package com.example.mental_health_companion.controller;


import com.example.mental_health_companion.domain.service.IChatbotService;
import com.example.mental_health_companion.dto.ChatRequestDto;
import com.example.mental_health_companion.dto.ChatResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/chatbot")
@RequiredArgsConstructor
@Slf4j
public class ChatbotController {

    private final IChatbotService chatbotService;

    @PostMapping("/chat")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<ChatResponseDto> chatWithBot(@Valid @RequestBody ChatRequestDto requestDto) {
        log.info("Received chat message from student: {}", requestDto.getMessage());
        ChatResponseDto responseDto = chatbotService.getChatbotResponse(requestDto.getMessage());
        return ResponseEntity.ok(responseDto);
    }
}
