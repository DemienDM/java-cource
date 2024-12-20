package org.example.homework04.repository;

import org.example.homework04.domain.repository.RoleRepository;
import org.example.homework04.entity.Role;
import org.example.homework04.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class RoleRepositoryImpl implements RoleRepository {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Role create(Role role) {
        try (Session session = sessionFactory.openSession()) {
            session.save(role);
        }

        return role;
    }

    @Override
    public Optional<Role> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(Role.class, id));
        }
    }
}
