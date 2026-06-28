-- V10: Extend system_parameters for grouped configuration management
ALTER TABLE system_parameters ADD COLUMN param_group VARCHAR(50) NOT NULL DEFAULT 'DEFAULT';
ALTER TABLE system_parameters ADD COLUMN enabled BOOLEAN NOT NULL DEFAULT TRUE;
ALTER TABLE system_parameters ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

UPDATE system_parameters SET param_group = 'OTP' WHERE param_key LIKE 'otp.%';
UPDATE system_parameters SET param_key = 'expiry.minutes' WHERE param_key = 'otp.expiry.minutes';
UPDATE system_parameters SET param_key = 'max.retry' WHERE param_key = 'otp.max.retry';

ALTER TABLE system_parameters DROP CONSTRAINT uk_system_parameters_key;
ALTER TABLE system_parameters ADD CONSTRAINT uk_system_parameters_group_key UNIQUE (param_group, param_key);
