package org.example.homework03.validator;

import org.example.homework03.domain.repository.UserRepository;
import org.example.homework03.dto.UserRegistrationDto;
import org.example.homework03.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserValidatorTest {

    @Mock
    private UserRepository userRepository;

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
        assertTrue(userValidator.getErrors().contains("`Email` can not be empty."));
    }

    @Test
    void getError_EmailIsNotUnique() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(
                "my@gmail.com",
                "+34 301 539-0605",
                "$StrongPassword012",
                "$StrongPassword012"
        );

        User user = new User(
                1L,
                "my@gmail.com",
                "+34 301 539-0605",
                "$StrongPassword012"
        );

        when(userRepository.findByEmail(userRegistrationDto.email()))
                .thenReturn(Optional.of(user));

        boolean isValid = userValidator.isValid(userRegistrationDto);
        assertFalse(isValid);
        assertTrue(userValidator.getErrors().contains("`User` already exists."));
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
        assertFalse(userValidator.hasErrors());
    }
}