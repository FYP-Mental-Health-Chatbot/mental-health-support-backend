package com.example.mental_health_companion.serviceImpl;

import com.example.mental_health_companion.dto.ChatResponseDto;
import com.example.mental_health_companion.dto.LLMResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType; // <-- Imported MediaType
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class LLMIntegrationService {

    @Value("${llm.api.endpoint}")
    private String llmApiEndpoint;

    @Value("${llm.api.key}")
    private String llmApiKey;

    private final RestTemplate restTemplate;

//    public ChatResponseDto getResponseFromLLM(String prompt) {
//        log.info("Sending prompt to LLM: {}", prompt);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + llmApiKey);
//        headers.setContentType(MediaType.APPLICATION_JSON); // <-- Using constant
//
//        // Example request body for a generic LLM API
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("prompt", prompt);
//        requestBody.put("max_tokens", 150);
//
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
//
//        try {
//            ResponseEntity<LLMResponseDto> response = restTemplate.exchange( // <-- Using new DTO
//                    llmApiEndpoint,
//                    HttpMethod.POST,
//                    entity,
//                    LLMResponseDto.class // <-- Using new DTO
//            );
//
//            LLMResponseDto llmResponseDto = response.getBody();
//            if (llmResponseDto != null && llmResponseDto.getText() != null) {
//                log.info("Received response from LLM: {}", llmResponseDto.getText());
//                return new ChatResponseDto(llmResponseDto.getText());
//            }
//
//            log.warn("Received empty or invalid response from LLM API.");
//            return new ChatResponseDto("I received an invalid response. Please try again.");
//
//        } catch (Exception e) {
//            log.error("Failed to get response from LLM API: {}", e.getMessage());
//            // Return a default, helpful response instead of an error
//            return new ChatResponseDto("I'm sorry, I couldn't connect to the service right now. Please try again later.");
//        }
//    }
}
