-- V8: Add last login timestamp for session security tracking
ALTER TABLE users ADD COLUMN last_login_at TIMESTAMP;
