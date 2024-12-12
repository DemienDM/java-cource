package org.example.homework03.validator;

import org.example.homework03.domain.service.UserService;
import org.example.homework03.dto.UserRegistrationDto;
import org.example.homework03.dto.UserResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserValidatorTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserValidator userValidator;

    @Test
    void getError_EmailIsNull() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(
                null,
                "+34 301 539-0605",
                "$StrongPassword012",
                "$StrongPassword012"
        );

        boolean isValid = userValidator.isValid(userRegistrationDto);
        assertFalse(isValid);
        assertEquals("`Email` can not be empty.", userValidator.getError());
    }

    @Test
    void getError_EmailIsNotUnique() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(
                "my@gmail.com",
                "+34 301 539-0605",
                "$StrongPassword012",
                "$StrongPassword012"
        );

        UserResponseDto userResponseDto = new UserResponseDto(
                1L,
                "my@gmail.com",
                "+34 301 539-0605"
        );

        when(userService.getUserByEmail(userRegistrationDto.email()))
                .thenReturn(Optional.of(userResponseDto));

        boolean isValid = userValidator.isValid(userRegistrationDto);
        assertFalse(isValid);
        assertEquals("`User` already exists.", userValidator.getError());
    }

    @Test
    void getError_Success() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(
                "my@gmail.com",
                "+34 301 539-0605",
                "$StrongPassword012",
                "$StrongPassword012"
        );

        boolean isValid = userValidator.isValid(userRegistrationDto);
        assertTrue(isValid);
        assertNull(userValidator.getError());
    }
}