package com.team_yerng.l_s_m_s.controllers;

import com.team_yerng.l_s_m_s.dto.AuthResponse;
import com.team_yerng.l_s_m_s.dto.LoginRequest;
import com.team_yerng.l_s_m_s.dto.UserDto;
import com.team_yerng.l_s_m_s.service.AuthService;
import com.team_yerng.l_s_m_s.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDto>> register(@Valid @RequestBody UserDto dto) {
        UserDto user = authService.register(dto);
        return ResponseEntity.status(201).body(ApiResponse.<UserDto>builder()
                .success(true)
                .code(201)
                .message("Register success")
                .data(user)
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(ApiResponse.<AuthResponse>builder()
                .success(true)
                .code(200)
                .message("Login success")
                .data(response)
                .build());
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<String>> refresh(@RequestParam String refreshToken) {
        String accessToken = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .code(200)
                .message("Access token refreshed")
                .data(accessToken)
                .build());
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Object>> logout(@RequestParam String refreshToken) {
        authService.logout(refreshToken);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .code(200)
                .message("Logout success")
                .data(null)
                .build());
    }
}

