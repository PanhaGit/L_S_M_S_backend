package com.team_yerng.l_s_m_s.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String fullName;
    private String email;
    private Integer roleId;
    private Boolean status;
    private String password;
}
