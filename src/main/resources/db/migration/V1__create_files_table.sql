CREATE TABLE files
(
    file_id     UUID             NOT NULL,
    file_name   VARCHAR(200),
    file_url    VARCHAR(150),
    upload_date TIMESTAMP WITHOUT TIME ZONE,
    file_size   DOUBLE PRECISION NOT NULL,
    file_type   VARCHAR(8),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_files PRIMARY KEY (file_id)
);