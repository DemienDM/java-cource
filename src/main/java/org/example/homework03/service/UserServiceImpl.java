package org.example.homework03.service;

import org.example.homework03.domain.repository.UserRepository;
import org.example.homework03.domain.service.UserService;
import org.example.homework03.dto.UserRegistrationDto;
import org.example.homework03.dto.UserResponseDto;
import org.example.homework03.entity.User;
import org.example.homework03.exception.EntityNotFoundException;
import org.example.homework03.mapper.UserMapper;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto register(UserRegistrationDto userRegistrationDto) {
        User user = userRepository.persist(UserMapper.toEntity(userRegistrationDto));

        return UserMapper.toResponseDto(user);
    }

    public UserResponseDto getById(Long userId) {
        return userRepository.findById(userId)
                .map(UserMapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
    }

    public UserResponseDto getByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found with Email: " + email));
    }
}
