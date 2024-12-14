package org.example.homework03.domain.service;

import org.example.homework03.domain.repository.UserRepository;
import org.example.homework03.dto.UserRegistrationDto;
import org.example.homework03.dto.UserResponseDto;
import org.example.homework03.entity.User;
import org.example.homework03.service.UserServiceImpl;
import org.example.homework03.validator.UserValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private UserValidator userValidator;

    @Test
    void registerUser_Success() {
        User user = new User(
                1L,
                "my@gmail.com",
                "+34 301 539-0605",
                "$StrongPassword012"
        );

        when(userRepository.persist(any(User.class))).thenReturn(user);

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(
                "my@gmail.com",
                "+34 301 539-0605",
                "$StrongPassword012",
                "$StrongPassword012"
        );

        UserResponseDto userResponseDto = userService.register(userRegistrationDto);

        UserResponseDto userResponseDtoExpected = new UserResponseDto(
                1L,
                "my@gmail.com",
                "+34 301 539-0605"
        );

        assertEquals(userResponseDtoExpected, userResponseDto);
    }

    @Test
    void getUserById_Success() {
        User user = new User(
                1L,
                "my@gmail.com",
                "+34 301 539-0605",
                "$StrongPassword012"
        );

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDto userResponseDto = userService.getById(1L);

        UserResponseDto userResponseDtoExpected = new UserResponseDto(
                1L,
                "my@gmail.com",
                "+34 301 539-0605"
        );

        assertEquals(userResponseDtoExpected, userResponseDto);
    }

    @Test
    void getUserByEmail_Success() {
        User user = new User(
                1L,
                "my@gmail.com",
                "+34 301 539-0605",
                "$StrongPassword012"
        );

        when(userRepository.findByEmail("my@gmail.com")).thenReturn(Optional.of(user));

        UserResponseDto userResponseDto = userService.getByEmail(user.getEmail());

        UserResponseDto userResponseDtoExpected = new UserResponseDto(
                1L,
                "my@gmail.com",
                "+34 301 539-0605"
        );

        assertEquals(userResponseDtoExpected, userResponseDto);
    }
}
