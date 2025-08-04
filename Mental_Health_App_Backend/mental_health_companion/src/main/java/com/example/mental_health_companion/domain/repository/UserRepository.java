package com.example.mental_health_companion.domain.repository;

import com.example.mental_health_companion.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA automatically provides the implementation for this method
    Optional<User> findByEmail(String email);

}
