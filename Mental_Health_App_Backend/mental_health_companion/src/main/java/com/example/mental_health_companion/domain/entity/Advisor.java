package com.example.mental_health_companion.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

    private Integer maxCapacity = 10;

    @OneToMany(mappedBy = "assignedAdvisor", cascade = CascadeType.ALL)
    private List<Student> assignedStudents = new ArrayList<>();
}
