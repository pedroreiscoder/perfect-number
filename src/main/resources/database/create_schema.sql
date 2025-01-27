CREATE SCHEMA IF NOT EXISTS perfect_number_schema;

CREATE TABLE IF NOT EXISTS perfect_number_schema.audit_logs (
    id BIGSERIAL PRIMARY KEY,
    ip_address VARCHAR(45) NOT NULL,
    perfect_numbers VARCHAR(150) NOT NULL
);
