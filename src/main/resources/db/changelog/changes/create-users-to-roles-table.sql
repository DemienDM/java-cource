--liquibase formatted sql
--changeset dyermola:create-users-to-roles-table
CREATE TABLE java_course.users_to_roles (
	user_id BIGINT UNSIGNED NOT NULL,
	role_id BIGINT UNSIGNED NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb3
COLLATE=utf8mb3_general_ci;
CREATE UNIQUE INDEX users_to_roles_user_id_IDX USING BTREE ON java_course.users_to_roles (user_id,role_id);

--rollback DROP TABLE users_to_roles;
