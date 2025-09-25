package com.team_yerng.l_s_m_s.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String fullName;
    private Integer roleId; // 1=Admin, 2=User etc.
}
