-- V1: Users and roles (SQL Server)
CREATE TABLE users (
    id              BIGINT IDENTITY(1,1) PRIMARY KEY,
    username        VARCHAR(50)  NOT NULL,
    password        VARCHAR(255) NOT NULL,
    email           VARCHAR(100) NOT NULL,
    full_name       VARCHAR(100) NOT NULL,
    mobile          VARCHAR(20)  NOT NULL,
    national_id     VARCHAR(20)  NOT NULL,
    status          VARCHAR(20)  NOT NULL DEFAULT 'ACTIVE',
    created_at      DATETIME2    NOT NULL DEFAULT GETDATE(),
    updated_at      DATETIME2    NOT NULL DEFAULT GETDATE(),
    CONSTRAINT uk_users_username UNIQUE (username),
    CONSTRAINT uk_users_email UNIQUE (email)
);

CREATE TABLE user_roles (
    id              BIGINT IDENTITY(1,1) PRIMARY KEY,
    user_id         BIGINT       NOT NULL,
    role            VARCHAR(30)  NOT NULL,
    created_at      DATETIME2    NOT NULL DEFAULT GETDATE(),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT uk_user_roles_user_role UNIQUE (user_id, role)
);

CREATE INDEX idx_users_status ON users(status);
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_user_roles_user_id ON user_roles(user_id);
