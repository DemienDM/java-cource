package org.example.homework03;

import org.example.homework03.domain.service.UserService;
import org.example.homework03.dto.UserRegistrationDto;
import org.example.homework03.dto.UserResponseDto;
import org.example.homework03.repository.UserRepositoryImpl;
import org.example.homework03.service.UserServiceImpl;
import org.example.homework03.validator.UserValidator;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(
                "my@gmail.com",
                "+34 301 539-0605",
                "$StrongPassword012",
                "$StrongPassword012"
        );

        UserValidator userValidator = new UserValidator(new UserServiceImpl(new UserRepositoryImpl()));

        if (!userValidator.isValid(userRegistrationDto)) {
            System.out.println(userValidator.getError());
            return;
        }

        UserService userService = new UserServiceImpl(new UserRepositoryImpl());

        UserResponseDto userResponseDto = userService.registerUser(userRegistrationDto);
        System.out.println(userResponseDto);

        Optional<UserResponseDto> optionalDto = userService.getUserById(userResponseDto.userId());

        optionalDto.ifPresentOrElse(System.out::println, () -> System.out.println("User does not exist."));
    }
}
