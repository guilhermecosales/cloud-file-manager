CREATE TABLE users
(
    user_id    UUID                NOT NULL,
    full_name  VARCHAR(100)        NOT NULL,
    username   VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR             NOT NULL,
    role       VARCHAR(15)         NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);