-- V10: Extend system_parameters for grouped configuration management (SQL Server)
ALTER TABLE system_parameters ADD param_group VARCHAR(50) NOT NULL DEFAULT 'DEFAULT';
ALTER TABLE system_parameters ADD enabled BIT NOT NULL DEFAULT 1;
ALTER TABLE system_parameters ADD created_at DATETIME2 NOT NULL DEFAULT GETDATE();

UPDATE system_parameters SET param_group = 'OTP' WHERE param_key LIKE 'otp.%';
UPDATE system_parameters SET param_key = 'expiry.minutes' WHERE param_key = 'otp.expiry.minutes';
UPDATE system_parameters SET param_key = 'max.retry' WHERE param_key = 'otp.max.retry';

ALTER TABLE system_parameters DROP CONSTRAINT uk_system_parameters_key;
ALTER TABLE system_parameters ADD CONSTRAINT uk_system_parameters_group_key UNIQUE (param_group, param_key);

CREATE INDEX idx_system_parameters_group ON system_parameters(param_group);
