package com.example.mental_health_companion.domain.service;

import com.example.mental_health_companion.domain.entity.FcmToken;

public interface INotificationService {
    void sendFCMNotification(FcmToken fcmToken, String s, String s1);
}
