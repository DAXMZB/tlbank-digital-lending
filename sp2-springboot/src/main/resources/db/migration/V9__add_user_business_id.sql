-- V9: Add business user identifier separate from login username
ALTER TABLE users ADD COLUMN user_id VARCHAR(50);

UPDATE users SET user_id = 'USR-SEED0001' WHERE username = 'admin' AND user_id IS NULL;
UPDATE users SET user_id = 'USR-SEED0002' WHERE username = 'reviewer1' AND user_id IS NULL;
UPDATE users SET user_id = 'USR-SEED0003' WHERE username = 'applicant1' AND user_id IS NULL;
UPDATE users SET user_id = 'USR-' || SUBSTRING(REPLACE(CAST(RANDOM_UUID() AS VARCHAR(36)), '-', ''), 1, 8)
WHERE user_id IS NULL;

ALTER TABLE users ALTER COLUMN user_id SET NOT NULL;
ALTER TABLE users ADD CONSTRAINT uk_users_user_id UNIQUE (user_id);
