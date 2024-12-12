package org.example.homework03.mapper;

import org.example.homework03.dto.UserRegistrationDto;
import org.example.homework03.dto.UserResponseDto;
import org.example.homework03.entity.User;

public class UserMapper {
    public static User toEntity(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setEmail(userRegistrationDto.email());
        user.setPhoneNumber(userRegistrationDto.phoneNumber());
        user.setPassword(userRegistrationDto.password());

        return user;
    }

    public static UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getPhoneNumber()
        );
    }

}
