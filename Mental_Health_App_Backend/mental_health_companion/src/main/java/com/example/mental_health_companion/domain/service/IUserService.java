package com.example.mental_health_companion.domain.service;

import com.example.mental_health_companion.domain.entity.User;
import com.example.mental_health_companion.dto.UserProfileDto;

import java.util.Optional;

public interface IUserService {

    UserProfileDto getUserProfile(Long userId);

    User saveUser(User user);

     Optional<User> findByEmail(String email);

}