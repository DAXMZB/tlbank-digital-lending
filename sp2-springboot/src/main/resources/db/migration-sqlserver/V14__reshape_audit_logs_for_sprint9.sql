-- V14: Reshape audit_logs for Sprint 9 audit module (SQL Server)
IF OBJECT_ID('audit_logs', 'U') IS NOT NULL
    DROP TABLE audit_logs;

CREATE TABLE audit_logs (
    id                  BIGINT IDENTITY(1,1) PRIMARY KEY,
    username            VARCHAR(100)   NULL,
    action              VARCHAR(50)    NOT NULL,
    ip_address          VARCHAR(45)    NULL,
    result              VARCHAR(20)    NOT NULL,
    detail              VARCHAR(500)   NULL,
    created_at          DATETIME2      NOT NULL DEFAULT GETDATE()
);

CREATE INDEX idx_audit_logs_username ON audit_logs(username);
CREATE INDEX idx_audit_logs_action ON audit_logs(action);
CREATE INDEX idx_audit_logs_created_at ON audit_logs(created_at);
