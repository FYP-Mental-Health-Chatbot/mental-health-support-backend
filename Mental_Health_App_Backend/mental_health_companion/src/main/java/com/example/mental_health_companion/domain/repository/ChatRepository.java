package com.example.mental_health_companion.domain.repository;

import com.example.mental_health_companion.domain.entity.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<ChatSession, Long> {
    Optional<ChatSession> findBySessionId(Long sessionId);

//    Optional<ChatSession> findByStudentId(Long studentId);

//    Optional<ChatSession> findByStudent_StudentId(Long studentId);

    Optional<ChatSession> findByStudent_StudentDetails_StudentId(Long studentId);
}
