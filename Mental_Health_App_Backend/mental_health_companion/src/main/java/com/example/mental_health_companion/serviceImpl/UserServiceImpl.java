package com.example.mental_health_companion.serviceImpl;

import com.example.mental_health_companion.domain.entity.User;
import com.example.mental_health_companion.domain.repository.UserRepository;
import com.example.mental_health_companion.domain.service.IUserService;
import com.example.mental_health_companion.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Override
    public UserProfileDto getUserProfile(Long userId) {
        log.info("Attempting to retrieve user profile for userId: {}", userId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            log.info("User found: {}", user.getEmail());
            // Map to DTO
            return new UserProfileDto(user.getFullName(), user.getEmail());
        } else {
            log.warn("User with userId: {} not found.", userId);
            throw new RuntimeException("User not found."); // Or a custom exception
        }
    }

    @Override
    public User saveUser(User user) {
        // Here you would add business logic like password hashing before saving
        log.info("Saving new user: {}", user.getEmail());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("Finding user by email: {}", email);
        return userRepository.findByEmail(email);
    }
}