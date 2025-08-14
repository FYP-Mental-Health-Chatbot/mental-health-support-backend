package com.example.mental_health_companion.controller;

import com.example.mental_health_companion.domain.entity.Advisor;
import com.example.mental_health_companion.domain.entity.ChatSession;
import com.example.mental_health_companion.domain.entity.Student;
import com.example.mental_health_companion.domain.service.IAdvisorService;
import com.example.mental_health_companion.domain.service.IChatbotService;
import com.example.mental_health_companion.domain.service.INotificationService;
import com.example.mental_health_companion.domain.service.IStudentService;
import com.example.mental_health_companion.dto.HandoverRequest;
import com.example.mental_health_companion.enums.ChatState;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("/api/handoff")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Handoff", description = "Endpoints for interacting with the AI chatbot handover")
public class HandoffController {

    @Autowired
    private INotificationService notificationService;

    @Autowired
    private IAdvisorService advisorService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IChatbotService chatbotService;

    @PostMapping("/handover")
    public ResponseEntity<String> handleHandoff(@RequestBody HandoverRequest handoverRequest) {
        Long studentId = handoverRequest.getStudentId();
        String reason = handoverRequest.getReason();

        Advisor advisor = advisorService.findAdvisorByStudentId(studentId);

        if (advisor != null && advisor.getUser().getFcmToken() != null) {

            ChatSession chatSession = chatbotService.getChatSessionByStudentId(studentId);
            // Update the chat state to "ADVISOR_ACTIVE"
            chatSession.setChatState(ChatState.ADVISOR);
            chatSession.setAdvisor(advisor.getUser());
            Student student = studentService.findStudentByStudentId(studentId);
            // Send a push notification to the advisor
            notificationService.sendFCMNotification(
                    advisor.getUser().getFcmToken(),
                    "URGENT: Student Escalation",
                    "Student " + studentId + " has requested help. Reason: " + reason
            );
            return ResponseEntity.ok("Advisor notified successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Advisor not found or notification failed.");
    }

    @PostMapping("/handback")
    public ResponseEntity<String> handleHandback(@RequestBody Map<String, String> payload) {
        Long studentId = Long.parseLong(payload.get("studentId"));


        ChatSession chatSession = chatbotService.getChatSessionByStudentId(studentId);
        chatSession.setChatState(ChatState.BOT);
        chatSession.setAdvisor(null);

        Student student = studentService.findStudentByStudentId(studentId);
        // Optionally, send a notification to the student
        notificationService.sendFCMNotification(
                // Get student token
                student.getUser().getFcmToken(),
                "Advisor Handback",
                "You are now talking to the chatbot again."
        );

        return ResponseEntity.ok("Bot is now active for student " + studentId);
    }
}
