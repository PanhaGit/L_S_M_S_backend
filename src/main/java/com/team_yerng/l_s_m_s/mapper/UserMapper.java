package com.team_yerng.l_s_m_s.mapper;

import com.team_yerng.l_s_m_s.dto.UserDto;
import com.team_yerng.l_s_m_s.model.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) return null;

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setRoleId(user.getRoleId());
        dto.setStatus(user.getStatus());
        // dto.setPassword(user.getPassword());
        return dto;
    }

    public static User toEntity(UserDto dto) {
        if (dto == null) return null;

        return User.builder()
                .id(dto.getId())
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .roleId(dto.getRoleId())
                .status(dto.getStatus() != null ? dto.getStatus() : true)
                .password(dto.getPassword()) // include password if registering
                .build();
    }
}
