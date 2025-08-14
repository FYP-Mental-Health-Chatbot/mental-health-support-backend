package com.example.mental_health_companion.serviceImpl;

import com.example.mental_health_companion.domain.service.IMessageToAdvisorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageToAdvisorServiceImpl implements IMessageToAdvisorService {
    @Override
    public void sendMessageToAdvisor(Long receiverId, String messageText) {

    }
}
