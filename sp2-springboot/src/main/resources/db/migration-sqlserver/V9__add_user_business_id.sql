-- V9: Add business user identifier separate from login username (SQL Server)
-- 注意：SQL Server 必須用 GO 分批；同 batch 內 ADD 後立刻 UPDATE 新欄位會解析失敗（Invalid column name 'user_id'）
ALTER TABLE users ADD user_id VARCHAR(50) NULL;
GO

UPDATE users SET user_id = 'USR-SEED0001' WHERE username = 'admin' AND user_id IS NULL;
UPDATE users SET user_id = 'USR-SEED0002' WHERE username = 'reviewer1' AND user_id IS NULL;
UPDATE users SET user_id = 'USR-SEED0003' WHERE username = 'applicant1' AND user_id IS NULL;
UPDATE users SET user_id = 'USR-' + SUBSTRING(REPLACE(CAST(NEWID() AS VARCHAR(36)), '-', ''), 1, 8)
WHERE user_id IS NULL;
GO

ALTER TABLE users ALTER COLUMN user_id VARCHAR(50) NOT NULL;
ALTER TABLE users ADD CONSTRAINT uk_users_user_id UNIQUE (user_id);
GO
