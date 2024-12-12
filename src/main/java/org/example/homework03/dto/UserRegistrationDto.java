package org.example.homework03.dto;

public record UserRegistrationDto(String email, String phoneNumber, String password, String repeatPassword) {
}
