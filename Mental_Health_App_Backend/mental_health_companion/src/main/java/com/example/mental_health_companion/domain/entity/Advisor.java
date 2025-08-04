package com.example.mental_health_companion.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "advisor_details")
@Data
public class Advisor {

    @Id
    private Long advisorId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "advisor_id")
    private User user;

    private String specialization;

    private boolean isActive = true;
}
