package com.example.mental_health_companion.serviceImpl;

import com.example.mental_health_companion.domain.entity.ChatSession;
import com.example.mental_health_companion.domain.service.IChatSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatSessionServiceImpl implements IChatSessionService {

    @Override
    public ChatSession createSession(Long senderId) {
        return null;
    }
}
