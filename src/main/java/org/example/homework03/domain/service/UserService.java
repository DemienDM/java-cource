package org.example.homework03.domain.service;

import org.example.homework03.dto.UserRegistrationDto;
import org.example.homework03.dto.UserResponseDto;

import java.util.Optional;

public interface UserService {
    UserResponseDto registerUser(UserRegistrationDto userRegistrationDto);

    Optional<UserResponseDto> getUserById(Long userId);

    Optional<UserResponseDto> getUserByEmail(String email);
}
