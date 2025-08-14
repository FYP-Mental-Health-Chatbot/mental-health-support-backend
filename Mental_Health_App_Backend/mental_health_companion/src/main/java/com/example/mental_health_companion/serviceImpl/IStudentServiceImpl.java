package com.example.mental_health_companion.serviceImpl;

import com.example.mental_health_companion.domain.entity.Student;
import com.example.mental_health_companion.domain.repository.StudentRepository;
import com.example.mental_health_companion.domain.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class IStudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student findStudentByStudentId(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(()->new IllegalArgumentException("Cannot find a student with student id " + studentId));
    }
}
