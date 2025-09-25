package com.team_yerng.l_s_m_s.service;

import com.team_yerng.l_s_m_s.model.PersonalAccessTokens;
import com.team_yerng.l_s_m_s.repository.PersonalAccessTokensRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonalAccessTokensService {

    private final PersonalAccessTokensRepository repository;

    public PersonalAccessTokensService(PersonalAccessTokensRepository repository) {
        this.repository = repository;
    }

    public PersonalAccessTokens createRefreshToken(Long userId) {
        String token = UUID.randomUUID().toString();
        PersonalAccessTokens refreshToken = PersonalAccessTokens.builder()
                .userId(userId)
                .refreshToken(token)
                .issuedAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusDays(7))
                .build();
        return repository.save(refreshToken);
    }

    public Optional<PersonalAccessTokens> getByToken(String token) {
        return repository.findByRefreshToken(token);
    }

    public void deleteToken(String token) {
        repository.findByRefreshToken(token).ifPresent(repository::delete);
    }
}
