package com.example.mental_health_companion.domain.service;

import com.example.mental_health_companion.domain.entity.Advisor;

public interface IAdvisorService {
    Advisor findAdvisorByStudentId(Long studentId);
}
