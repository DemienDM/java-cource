package org.example.homework04;

import org.example.homework04.domain.repository.RoleRepository;
import org.example.homework04.domain.repository.UserRepository;
import org.example.homework04.entity.Role;
import org.example.homework04.entity.User;
import org.example.homework04.repository.RoleRepositoryImpl;
import org.example.homework04.repository.UserRepositoryImpl;

public class Main {
    public static void main(String[] args) {

        RoleRepository roleRepository = new RoleRepositoryImpl();

        // Create new Role.
        Role role = roleRepository.create(new Role(null, "Root"));
        System.out.println(role);

        // Find Role.
        roleRepository
                .findById(1L)
                .ifPresentOrElse(System.out::println, () -> System.out.println("Role not found."));

        UserRepository userRepository = new UserRepositoryImpl();

        // Create new User.
        User user = new User(
                null,
                "custom@gmail.com",
                null,
                "$VeryStrongPassword09"
        );

        user = userRepository.create(user);
        System.out.println(user);

        // Find User.
        userRepository
                .findById(1L)
                .ifPresentOrElse(System.out::println, () -> System.out.println("User not found."));

        // Update User.
        user.setEmail("custom.edited@gmail.com");
        user.setPhoneNumber("+34 301 539-0608");

        user = userRepository.update(user);
        System.out.println(user);
    }
}
