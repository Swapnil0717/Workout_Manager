package com.example.Workout.Manager.service;

import com.example.Workout.Manager.dto.SignupRequest;
import com.example.Workout.Manager.entity.User;
import com.example.Workout.Manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(SignupRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .provider("LOCAL")
                .roles(Set.of("ROLE_USER"))
                .build();

        userRepository.save(user);
    }
}
