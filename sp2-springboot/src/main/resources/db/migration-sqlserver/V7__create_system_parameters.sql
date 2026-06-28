-- V7: System parameters (SQL Server)
CREATE TABLE system_parameters (
    id                  BIGINT IDENTITY(1,1) PRIMARY KEY,
    param_key           VARCHAR(100)   NOT NULL,
    param_value         VARCHAR(500)   NOT NULL,
    description         VARCHAR(500),
    updated_at          DATETIME2      NOT NULL DEFAULT GETDATE(),
    CONSTRAINT uk_system_parameters_key UNIQUE (param_key)
);

CREATE INDEX idx_system_parameters_key ON system_parameters(param_key);
