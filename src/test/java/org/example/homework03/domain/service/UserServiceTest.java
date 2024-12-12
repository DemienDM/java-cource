package org.example.homework03.domain.service;

import org.example.homework03.domain.repository.UserRepository;
import org.example.homework03.dto.UserRegistrationDto;
import org.example.homework03.dto.UserResponseDto;
import org.example.homework03.entity.User;
import org.example.homework03.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void registerUser_Success() {
        User user = new User(
                1L,
                "my@gmail.com",
                "+34 301 539-0605",
                "$StrongPassword012"
        );

        when(userRepository.persistUser(any(User.class))).thenReturn(user);

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(
                "my@gmail.com",
                "+34 301 539-0605",
                "$StrongPassword012",
                "$StrongPassword012"
        );

        UserResponseDto userResponseDto = userService.registerUser(userRegistrationDto);

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

        when(userRepository.getUserById(1L)).thenReturn(Optional.of(user));

        Optional<UserResponseDto> optionalUserResponseDto = userService.getUserById(1L);

        UserResponseDto userResponseDtoExpected = new UserResponseDto(
                1L,
                "my@gmail.com",
                "+34 301 539-0605"
        );

        assertTrue(optionalUserResponseDto.isPresent());
        assertEquals(userResponseDtoExpected, optionalUserResponseDto.get());
    }

    @Test
    void getUserByEmail_Success() {
        User user = new User(
                1L,
                "my@gmail.com",
                "+34 301 539-0605",
                "$StrongPassword012"
        );

        when(userRepository.getUserByEmail("my@gmail.com")).thenReturn(Optional.of(user));

        Optional<UserResponseDto> optionalUserResponseDto = userService.getUserByEmail(user.getEmail());

        UserResponseDto userResponseDtoExpected = new UserResponseDto(
                1L,
                "my@gmail.com",
                "+34 301 539-0605"
        );

        assertTrue(optionalUserResponseDto.isPresent());
        assertEquals(userResponseDtoExpected, optionalUserResponseDto.get());
    }
}