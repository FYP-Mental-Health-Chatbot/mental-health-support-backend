package com.example.mental_health_companion.controller;


import com.example.mental_health_companion.domain.service.IChatbotService;
import com.example.mental_health_companion.dto.ChatMessageRequestDto;
import com.example.mental_health_companion.dto.ChatResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Chatbot", description = "Endpoints for interacting with the AI chatbot")
public class ChatbotController {

    private final IChatbotService chatbotService;

//    @Operation(
//            summary = "Send a message to the chatbot",
//            description = "Sends a text message and receives an AI-generated response. Requires student role.",
//            security = @SecurityRequirement(name = "Bearer Authentication")
//    )
//    @PostMapping("/chat")
//    @PreAuthorize("hasAuthority('STUDENT')")
//    public ResponseEntity<ChatResponseDto> chatWithBot(@Valid @RequestBody ChatMessageRequestDto requestDto) {
//        log.info("Received chat message from student: {}", requestDto.getMessage());
//        ChatResponseDto responseDto = chatbotService.getChatbotResponse(requestDto.getMessage());
//        return ResponseEntity.ok(responseDto);
//    }
}
