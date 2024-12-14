package org.example.homework03.domain.service;

import org.example.homework03.dto.UserRegistrationDto;
import org.example.homework03.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationDto userRegistrationDto);

    UserResponseDto getById(Long userId);

    UserResponseDto getByEmail(String email);
}
