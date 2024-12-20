--liquibase formatted sql
--changeset dyermola:insert-users-to-roles-table

INSERT INTO
    `users_to_roles`
VALUES
    (1, 1),
    (2, 2);

--rollback DELETE FROM users_to_roles WHERE (user_id = 1 AND role_id = 1) AND (user_id = 2 AND role_id = 2);
