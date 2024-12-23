package org.example.homework04.domain.repository;

import org.example.homework04.entity.User;

import java.util.Optional;

public interface UserRepository {
    User create(User user);

    User update(User user);

    Optional<User> findById(Long id);
}
