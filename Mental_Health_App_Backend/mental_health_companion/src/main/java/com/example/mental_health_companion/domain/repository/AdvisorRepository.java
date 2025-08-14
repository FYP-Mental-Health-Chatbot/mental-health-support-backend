package com.example.mental_health_companion.domain.repository;

import com.example.mental_health_companion.domain.entity.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
}
