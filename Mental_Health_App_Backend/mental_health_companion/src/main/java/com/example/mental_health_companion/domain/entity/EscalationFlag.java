package com.example.mental_health_companion.domain.entity;

import com.example.mental_health_companion.enums.FlagReason;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "escalation_flags")
@Data
public class EscalationFlag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private ChatSession chatSession;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "triggered_by_message_id", nullable = false)
    private Message triggeredByMessage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlagReason flagReason;

    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal riskScore;

    private LocalDateTime flaggedAt = LocalDateTime.now();

    private boolean isResolved = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resolved_by_advisor_id")
    private User resolvedByAdvisor;

    private LocalDateTime resolvedAt;
}
