--liquibase formatted sql
--changeset dyermola:insert-roles-table

INSERT INTO
    `roles`
VALUES
    (1, "User"),
    (2, "Manager"),
    (3, "Admin");

--rollback DELETE FROM roles WHERE id IN(1,2,3);
