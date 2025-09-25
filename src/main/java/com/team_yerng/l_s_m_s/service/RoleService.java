package com.team_yerng.l_s_m_s.service;

import com.team_yerng.l_s_m_s.dto.RoleDto;
import com.team_yerng.l_s_m_s.exception.UserNotFoundException;
import com.team_yerng.l_s_m_s.mapper.RoleMapper;
import com.team_yerng.l_s_m_s.model.Role;
import com.team_yerng.l_s_m_s.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleDto createRole(RoleDto dto) {
        Role role = RoleMapper.toEntity(dto);
        return RoleMapper.toDto(roleRepository.save(role));
    }

    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(RoleMapper::toDto)
                .collect(Collectors.toList());
    }

    public RoleDto getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)); // You can create RoleNotFoundException if you want
        return RoleMapper.toDto(role);
    }

    public RoleDto updateRole(Long id, RoleDto dto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        role.setName(dto.getName());
        role.setCode(dto.getCode());
        role.setDescription(dto.getDescription());

        return RoleMapper.toDto(roleRepository.save(role));
    }

    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        roleRepository.deleteById(id);
    }
}
