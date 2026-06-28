-- V8: Add last login timestamp for session security tracking (SQL Server)
ALTER TABLE users ADD last_login_at DATETIME2 NULL;
