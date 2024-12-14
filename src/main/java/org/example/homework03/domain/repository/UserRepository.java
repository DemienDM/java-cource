package org.example.homework03.domain.repository;

import org.example.homework03.entity.User;

import java.util.Optional;

public interface UserRepository {
    User persist(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
