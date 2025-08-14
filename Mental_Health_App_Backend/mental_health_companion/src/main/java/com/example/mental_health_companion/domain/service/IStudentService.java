package com.example.mental_health_companion.domain.service;

import com.example.mental_health_companion.domain.entity.Student;

public interface IStudentService {
    Student findStudentByStudentId(Long studentId);
}
