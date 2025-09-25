package com.team_yerng.l_s_m_s.exception;

public class RefreshTokenExpiredException extends RuntimeException {
    public RefreshTokenExpiredException() {
        super("Refresh token expired");
    }
}
