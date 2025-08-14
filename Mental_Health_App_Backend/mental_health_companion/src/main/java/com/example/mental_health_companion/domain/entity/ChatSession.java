package com.example.mental_health_companion.domain.entity;

import com.example.mental_health_companion.enums.ChatState;
import com.example.mental_health_companion.enums.SessionType;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_sessions")
@Data
public class ChatSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advisor_id")
    private User advisor;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private SessionType sessionType = SessionType.BOT;

    private boolean isActive = true;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime endedAt;

    private boolean isDeleted = false;

    private LocalDateTime deletedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChatState chatState = ChatState.BOT;
}
