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

    /**
     * Creates a new user. The UserDto must contain the plain text password,
     * which is then securely encoded before saving.
     * @param dto The UserDto containing user details, including the plain text password.
     * @return The saved UserDto.
     */
    public UserDto createUser(UserDto dto) {
        User user = UserMapper.toEntity(dto);

        // CRITICAL FIX: Ensure password is provided in DTO and encode it securely.
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required to create a new user.");
        }

        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Ensure ID is null for a new entity creation
        user.setId(null);

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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (dto.getFullName() != null) {
            user.setFullName(dto.getFullName());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getRoleId() != null) {
            user.setRoleId(dto.getRoleId());
        }
        if (dto.getStatus() != null) {
            user.setStatus(dto.getStatus());
        }

        // Handle password update if a new password is provided
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return UserMapper.toDto(userRepository.save(user));
    }


    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) throw new UserNotFoundException(id);
        userRepository.deleteById(id);
    }
}
