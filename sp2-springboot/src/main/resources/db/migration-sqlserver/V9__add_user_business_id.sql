-- V9: Add business user identifier separate from login username (SQL Server)
ALTER TABLE users ADD user_id VARCHAR(50) NULL;

UPDATE users SET user_id = 'USR-SEED0001' WHERE username = 'admin' AND user_id IS NULL;
UPDATE users SET user_id = 'USR-SEED0002' WHERE username = 'reviewer1' AND user_id IS NULL;
UPDATE users SET user_id = 'USR-SEED0003' WHERE username = 'applicant1' AND user_id IS NULL;
UPDATE users SET user_id = 'USR-' + SUBSTRING(REPLACE(CAST(NEWID() AS VARCHAR(36)), '-', ''), 1, 8)
WHERE user_id IS NULL;

ALTER TABLE users ALTER COLUMN user_id VARCHAR(50) NOT NULL;
ALTER TABLE users ADD CONSTRAINT uk_users_user_id UNIQUE (user_id);
