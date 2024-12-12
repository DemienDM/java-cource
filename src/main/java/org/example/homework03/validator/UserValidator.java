package org.example.homework03.validator;

import org.example.homework03.domain.service.UserService;
import org.example.homework03.dto.UserRegistrationDto;
import org.example.homework03.dto.UserResponseDto;

import java.util.Optional;
import java.util.regex.Pattern;

public class UserValidator {

    private String error;

    private final UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean isValid(UserRegistrationDto userRegistrationDto) {
        error = null;

        if (!checkEmail(userRegistrationDto.email())) {
            return false;
        }

        if (!checkPhoneNumber(userRegistrationDto.phoneNumber())) {
            return false;
        }

        return checkPassword(userRegistrationDto.password(), userRegistrationDto.repeatPassword());
    }

    private boolean checkEmail(String email) {
        if (email == null || email.isBlank()) {
            error = "`Email` can not be empty.";
            return false;
        }

        boolean isEmail = Pattern.compile("^(.+)@(\\S+)$")
                .matcher(email)
                .matches();

        if (!isEmail) {
            error = "`Email` has wrong format.";
            return false;
        }

        Optional<UserResponseDto> optionalUserResponseDto = userService.getUserByEmail(email);
        if (optionalUserResponseDto.isPresent()) {
            error = "`User` already exists.";
            return false;
        }

        return true;
    }

    private boolean checkPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return true;
        }

        boolean isPhoneNumber = Pattern.compile("^[+](?:[0-9\\-()/.]\\s?){6,15}[0-9]$")
                .matcher(phoneNumber)
                .matches();

        if (!isPhoneNumber) {
            error = "`Phone Number` has wrong format.";
        }

        return isPhoneNumber;
    }

    private boolean checkPassword(String password, String passwordRepeat) {
        if (password == null || password.isBlank() || passwordRepeat == null || passwordRepeat.isBlank()) {
            error = "`Password` & `PasswordRepeat` are required.";
            return false;
        }

        boolean isPassword = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$")
                .matcher(password)
                .matches();

        if (!isPassword) {
            error = "`Password` has wrong format.";
            return false;
        }

        if (!password.equals(passwordRepeat)) {
            error = "`Password` and `PasswordRepeat` are not equal.";
            return false;
        }

        return true;
    }

    public String getError() {
        return error;
    }
}
