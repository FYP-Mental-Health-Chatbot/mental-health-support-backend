package com.example.mental_health_companion.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_details")
@Data
public class Student {

    @Id
    private Long studentId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "student_id")
    private User user;

    @Column(unique = true, nullable = false)
    private String universityId;

    @Column(unique = true, nullable = false)
    private String studentEmail;

    @Column(unique = true, nullable = false)
    private String studentPhone;

    @Column(nullable = false)
    private String academicYear;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_advisor_id")
    private Advisor assignedAdvisor;
}
