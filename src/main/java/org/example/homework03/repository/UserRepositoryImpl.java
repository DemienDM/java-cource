package org.example.homework03.repository;

import org.example.homework03.domain.repository.UserRepository;
import org.example.homework03.entity.User;
import org.example.homework03.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String PASSWORD = "password";

    public User persist(User user) {
        String sql = "INSERT INTO users (email, phoneNumber, password) VALUES (?, ?, ?);";

        try (Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPhoneNumber());
            preparedStatement.setString(3, user.getPassword());

            int affectedRows = preparedStatement.executeUpdate();
            checkAffectedRowsNumber(affectedRows);
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long id = generatedKeys.getObject(1, Long.class);
                user.setId(id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection connection = ConnectionUtil.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(parseUser(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Method UserRepository.findById was failed with exception.", e);
        }

        return Optional.empty();
    }

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(parseUser(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Method UserRepository.findByEmail was failed with exception.", e);
        }

        return Optional.empty();
    }

    private User parseUser(ResultSet resultSet) {
        try {
            Long id = resultSet.getObject(ID, Long.class);
            String email = resultSet.getString(EMAIL);
            String phoneNumber = resultSet.getString(PHONE_NUMBER);
            String password = resultSet.getString(PASSWORD);

            return new User(
                    id,
                    email,
                    phoneNumber,
                    password
            );
        } catch (SQLException e) {
            throw new RuntimeException("Can not parse 'user' from resultSet.", e);
        }
    }

    private void checkAffectedRowsNumber(int num) {
        if (num == 0) {
            throw new RuntimeException("Database error: insert was failed.");
        }
    }
}
