CREATE TABLE personal_access_tokens
(
    id            bigint IDENTITY (1, 1) NOT NULL,
    user_id       bigint,
    refresh_token varchar(255),
    issued_at     datetime,
    expires_at    datetime,
    created_at    datetime,
    updated_at    datetime,
    CONSTRAINT pk_personal_access_tokens PRIMARY KEY (id)
)
    GO

CREATE TABLE role
(
    id          bigint IDENTITY (1, 1) NOT NULL,
    name        varchar(255),
    code        varchar(255) NOT NULL,
    description varchar(255),
    created_at  datetime,
    updated_at  datetime,
    CONSTRAINT pk_role PRIMARY KEY (id)
)
    GO

CREATE TABLE users
(
    id         bigint IDENTITY (1, 1) NOT NULL,
    full_name  varchar(255) NOT NULL,
    email      varchar(255) NOT NULL,
    password   varchar(255) NOT NULL,
    role_id    int          NOT NULL,
    status     bit          NOT NULL,
    created_at datetime,
    updated_at datetime,
    CONSTRAINT pk_users PRIMARY KEY (id)
)
    GO

ALTER TABLE personal_access_tokens
    ADD CONSTRAINT uc_personal_access_tokens_refreshtoken UNIQUE (refresh_token)
    GO

ALTER TABLE role
    ADD CONSTRAINT uc_role_code UNIQUE (code)
    GO

ALTER TABLE role
    ADD CONSTRAINT uc_role_name UNIQUE (name)
    GO

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email)
    GO