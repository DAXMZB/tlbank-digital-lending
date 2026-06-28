-- V6: Audit logs (SQL Server)
CREATE TABLE audit_logs (
    id                  BIGINT IDENTITY(1,1) PRIMARY KEY,
    entity_type         VARCHAR(50)    NOT NULL,
    entity_id           VARCHAR(50)    NOT NULL,
    action              VARCHAR(50)    NOT NULL,
    performed_by        BIGINT,
    details             VARCHAR(2000),
    created_at          DATETIME2      NOT NULL DEFAULT GETDATE(),
    CONSTRAINT fk_audit_logs_user FOREIGN KEY (performed_by) REFERENCES users(id)
);

CREATE INDEX idx_audit_logs_entity ON audit_logs(entity_type, entity_id);
CREATE INDEX idx_audit_logs_created_at ON audit_logs(created_at);
CREATE INDEX idx_audit_logs_performed_by ON audit_logs(performed_by);
