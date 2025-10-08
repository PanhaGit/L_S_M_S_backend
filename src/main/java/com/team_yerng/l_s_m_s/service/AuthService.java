package com.team_yerng.l_s_m_s.service;

import com.team_yerng.l_s_m_s.dto.AuthResponse;
import com.team_yerng.l_s_m_s.dto.UserDto;
import com.team_yerng.l_s_m_s.exception.*;
import com.team_yerng.l_s_m_s.model.PersonalAccessTokens;
import com.team_yerng.l_s_m_s.model.User;
import com.team_yerng.l_s_m_s.repository.PersonalAccessTokensRepository;
import com.team_yerng.l_s_m_s.repository.UserRepository;
import com.team_yerng.l_s_m_s.security.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PersonalAccessTokensRepository tokenRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PersonalAccessTokensRepository tokenRepository,
                       JwtUtils jwtUtils,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    // Register
    public UserDto register(UserDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException(dto.getEmail());
        }

        if (dto.getFullName() == null || dto.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Full name is required");
        }

        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }

        if (dto.getRoleId() == null) {
            throw new IllegalArgumentException("Role ID is required");
        }

        User user = User.builder()
                .fullName(dto.getFullName().trim())
                .email(dto.getEmail().trim())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roleId(dto.getRoleId())
                .status(dto.getStatus() != null ? dto.getStatus() : true)
                .build();

        User saved = userRepository.save(user);
        dto.setId(saved.getId());
        dto.setStatus(saved.getStatus());
        dto.setPassword(null);

        return dto;
    }

    // Login
    public AuthResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        // Generate tokens
        String accessToken = jwtUtils.generateAccessToken(user.getEmail());
        String refreshToken = jwtUtils.generateRefreshToken(user.getEmail());

        // Save refresh token in DB
        PersonalAccessTokens pat = PersonalAccessTokens.builder()
                .userId(user.getId())
                .refreshToken(refreshToken)
                .issuedAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusDays(7))
                .build();

        tokenRepository.save(pat);

        return new AuthResponse(accessToken, refreshToken);
    }

    // Refresh
    public String refreshToken(String refreshToken) {
        PersonalAccessTokens pat = tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(InvalidRefreshTokenException::new);

        if (pat.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RefreshTokenExpiredException();
        }

        User user = userRepository.findById(pat.getUserId())
                .orElseThrow(() -> new UserNotFoundException(pat.getUserId()));

        return jwtUtils.generateAccessToken(user.getEmail());
    }

    // Logout
    public void logout(String refreshToken) {
        PersonalAccessTokens pat = tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(InvalidRefreshTokenException::new);
        tokenRepository.delete(pat);
    }
}
