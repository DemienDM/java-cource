package org.example.homework03.domain.repository;

import org.example.homework03.entity.User;

import java.util.Optional;

public interface UserRepository {
    User persistUser(User user);

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);
}
