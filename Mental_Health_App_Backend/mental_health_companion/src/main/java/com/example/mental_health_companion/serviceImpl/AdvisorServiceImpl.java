package com.example.mental_health_companion.serviceImpl;

import com.example.mental_health_companion.domain.entity.Advisor;
import com.example.mental_health_companion.domain.entity.Student;
import com.example.mental_health_companion.domain.repository.AdvisorRepository;
import com.example.mental_health_companion.domain.repository.StudentRepository;
import com.example.mental_health_companion.domain.service.IAdvisorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdvisorServiceImpl implements IAdvisorService {
    @Autowired
    private AdvisorRepository advisorRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Advisor findAdvisorByStudentId(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student.map(Student::getAssignedAdvisor).orElse(null);
    }

    public Optional<Advisor> getAdvisor(Long advisorId) {
        return advisorRepository.findById(advisorId);
    }

    public void saveAdvisor(Advisor advisor) {
        advisorRepository.save(advisor);
    }


}
