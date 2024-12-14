package org.example.homework03.validator;

import org.example.homework03.domain.repository.UserRepository;
import org.example.homework03.dto.UserRegistrationDto;
import org.example.homework03.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserValidator {

    private List<String> errors = new ArrayList<>();

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isValid(UserRegistrationDto userRegistrationDto) {
        errors = new ArrayList<>();

        checkEmail(userRegistrationDto.email());
        checkPhoneNumber(userRegistrationDto.phoneNumber());
        checkPassword(userRegistrationDto.password(), userRegistrationDto.repeatPassword());

        return !hasErrors();
    }

    private void checkEmail(String email) {
        if (email == null || email.isBlank()) {
            errors.add("`Email` can not be empty.");
            return;
        }

        boolean isEmail = Pattern.compile("^(.+)@(\\S+)$")
                .matcher(email)
                .matches();

        if (!isEmail) {
            errors.add("`Email` has wrong format.");
        }

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            errors.add("`User` already exists.");
        }
    }

    private void checkPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return;
        }

        boolean isPhoneNumber = Pattern.compile("^[+](?:[0-9\\-()/.]\\s?){6,15}[0-9]$")
                .matcher(phoneNumber)
                .matches();

        if (!isPhoneNumber) {
            errors.add("`Phone Number` has wrong format.");
        }
    }

    private void checkPassword(String password, String passwordRepeat) {
        if (password == null || password.isBlank() || passwordRepeat == null || passwordRepeat.isBlank()) {
            errors.add("`Password` & `PasswordRepeat` are required.");
            return;
        }

        boolean isPassword = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$")
                .matcher(password)
                .matches();

        if (!isPassword) {
            errors.add("`Password` has wrong format.");
        }

        if (!password.equals(passwordRepeat)) {
            errors.add("`Password` and `PasswordRepeat` are not equal.");
        }
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
