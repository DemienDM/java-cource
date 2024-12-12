package org.example.homework03.service;

import org.example.homework03.domain.repository.UserRepository;
import org.example.homework03.domain.service.UserService;
import org.example.homework03.dto.UserRegistrationDto;
import org.example.homework03.dto.UserResponseDto;
import org.example.homework03.entity.User;
import org.example.homework03.mapper.UserMapper;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto registerUser(UserRegistrationDto userRegistrationDto) {
        User user = userRepository.persistUser(UserMapper.toEntity(userRegistrationDto));

        return UserMapper.toResponseDto(user);
    }

    public Optional<UserResponseDto> getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.getUserById(userId);

         if (optionalUser.isEmpty()) {
            return Optional.empty();
         }

         UserResponseDto userResponseDto = UserMapper.toResponseDto(optionalUser.get());

         return Optional.of(userResponseDto);
    }

    public Optional<UserResponseDto> getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.getUserByEmail(email);

        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }

        UserResponseDto userResponseDto = UserMapper.toResponseDto(optionalUser.get());

        return Optional.of(userResponseDto);
    }
}
