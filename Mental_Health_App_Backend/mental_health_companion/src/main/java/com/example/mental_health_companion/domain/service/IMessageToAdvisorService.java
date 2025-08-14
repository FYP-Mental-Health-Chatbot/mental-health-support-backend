package com.example.mental_health_companion.domain.service;

public interface IMessageToAdvisorService {
    void sendMessageToAdvisor(Long receiverId, String messageText);
}
