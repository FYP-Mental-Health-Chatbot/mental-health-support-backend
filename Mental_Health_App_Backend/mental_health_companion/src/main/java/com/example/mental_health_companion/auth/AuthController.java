package com.example.mental_health_companion.auth;

import com.example.mental_health_companion.auth.request.RegisterRequest;
import com.example.mental_health_companion.domain.entity.User;
import com.example.mental_health_companion.domain.service.IUserService;
import com.example.mental_health_companion.security.JwtTokenProvider;
import com.example.mental_health_companion.auth.request.LoginRequest;
import com.example.mental_health_companion.auth.response.JwtResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userService.findByEmail(registerRequest.getEmail()).isPresent()) {
            return new ResponseEntity<>("Error: Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setFullName(registerRequest.getFullName());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        user.setUserType(registerRequest.getUserType());

        userService.saveUser(user);
        log.info("New user registered successfully: {}", user.getEmail());

        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("Attempting login for user: {}", loginRequest.getEmail());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken((UserDetails) authentication.getPrincipal());
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String role = userDetails.getAuthorities().iterator().next().getAuthority();

        log.info("Login successful for user: {}", loginRequest.getEmail());

        return ResponseEntity.ok(new JwtResponse(jwt, "Bearer", userDetails.getUsername(), role));
    }
}
