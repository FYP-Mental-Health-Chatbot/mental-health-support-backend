package com.example.mental_health_companion.domain.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private ChatSession chatSession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Column(nullable = false)
    private Long studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = true)
    private User receiver;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String messageText;

    private LocalDateTime sentAt = LocalDateTime.now();

    private boolean isDeleted = false;

    private LocalDateTime deletedAt;

    private String messageSource; // e.g., "bot", "student", "advisor"
}
