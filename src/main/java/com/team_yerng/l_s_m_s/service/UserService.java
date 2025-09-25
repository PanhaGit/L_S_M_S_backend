package com.team_yerng.l_s_m_s.service;

import com.team_yerng.l_s_m_s.dto.UserDto;
import com.team_yerng.l_s_m_s.exception.UserNotFoundException;
import com.team_yerng.l_s_m_s.mapper.UserMapper;
import com.team_yerng.l_s_m_s.model.User;
import com.team_yerng.l_s_m_s.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(UserDto dto) {
        User user = UserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode("123456"));
        return UserMapper.toDto(userRepository.save(user));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return UserMapper.toDto(user);
    }

    public UserDto updateUser(Long id, UserDto dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setRoleId(dto.getRoleId());
        user.setStatus(dto.getStatus());
        return UserMapper.toDto(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) throw new UserNotFoundException(id);
        userRepository.deleteById(id);
    }
}
