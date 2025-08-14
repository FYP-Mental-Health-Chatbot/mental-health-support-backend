package com.example.mental_health_companion.domain.service;

import com.example.mental_health_companion.domain.entity.ChatSession;

public interface IChatSessionService {
    ChatSession createSession(Long senderId);
}
