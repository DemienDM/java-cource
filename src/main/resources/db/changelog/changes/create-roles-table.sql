--liquibase formatted sql
--changeset dyermola:create-roles-table
CREATE TABLE java_course.roles (
	id BIGINT UNSIGNED auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT roles_pk PRIMARY KEY (id),
	CONSTRAINT roles_unique UNIQUE KEY (name)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb3
COLLATE=utf8mb3_general_ci;

--rollback DROP TABLE roles;
