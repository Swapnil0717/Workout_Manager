package com.example.Workout.Manager.controller;

import com.example.Workout.Manager.dto.AuthResponse;
import com.example.Workout.Manager.dto.LoginRequest;
import com.example.Workout.Manager.dto.RefreshTokenRequest;
import com.example.Workout.Manager.dto.SignupRequest;
import com.example.Workout.Manager.security.JwtUtil;
import com.example.Workout.Manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Value("${jwt.access-expiration}")
    private long accessExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        userService.register(request);
        return ResponseEntity.ok(Map.of(
                "message", "User registered successfully"
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        String accessToken =
                jwtUtil.generateToken(request.getEmail(), accessExpiration);

        String refreshToken =
                jwtUtil.generateToken(request.getEmail(), refreshExpiration);

        return ResponseEntity.ok(
                AuthResponse.builder()
                        .message("Logged in successfully")
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build()
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(
            @RequestBody RefreshTokenRequest request) {

        String email =
                jwtUtil.extractUsername(request.getRefreshToken());

        String newAccessToken =
                jwtUtil.generateToken(email, accessExpiration);

        return ResponseEntity.ok(
                AuthResponse.builder()
                        .message("Token refreshed successfully")
                        .accessToken(newAccessToken)
                        .build()
        );
    }
}
