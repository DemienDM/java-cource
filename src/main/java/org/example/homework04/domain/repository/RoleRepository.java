package org.example.homework04.domain.repository;

import org.example.homework04.entity.Role;

import java.util.Optional;

public interface RoleRepository {

    Role create(Role role);

    Optional<Role> findById(Long id);
}
