package com.example.mental_health_companion.serviceImpl;

import com.example.mental_health_companion.domain.entity.FcmToken;
import com.example.mental_health_companion.domain.service.INotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements INotificationService {
    @Override
    public void sendFCMNotification(FcmToken fcmToken, String s, String s1) {
        try {
            Message message = Message.builder()
                    .setNotification(Notification.builder().setTitle(s).setBody(s1).build())
                    .setToken(fcmToken.getFcmToken())
                    .build();
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }
}
