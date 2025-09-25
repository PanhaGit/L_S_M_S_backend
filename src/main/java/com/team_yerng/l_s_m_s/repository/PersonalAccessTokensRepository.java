package com.team_yerng.l_s_m_s.repository;

import com.team_yerng.l_s_m_s.model.PersonalAccessTokens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalAccessTokensRepository extends JpaRepository<PersonalAccessTokens, Long> {
    Optional<PersonalAccessTokens> findByRefreshToken(String refreshToken);
}
