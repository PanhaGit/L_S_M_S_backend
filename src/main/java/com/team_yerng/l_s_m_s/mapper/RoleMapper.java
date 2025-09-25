package com.team_yerng.l_s_m_s.mapper;

import com.team_yerng.l_s_m_s.dto.RoleDto;
import com.team_yerng.l_s_m_s.model.Role;

public class RoleMapper {

    public static RoleDto toDto(Role role) {
        if (role == null) return null;
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .code(role.getCode())
                .description(role.getDescription())
                .build();
    }

    public static Role toEntity(RoleDto dto) {
        if (dto == null) return null;
        return Role.builder()
                .id(dto.getId())
                .name(dto.getName())
                .code(dto.getCode())
                .description(dto.getDescription())
                .build();
    }
}
