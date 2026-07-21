-- V10: Extend system_parameters for grouped configuration management (SQL Server)
-- 注意：SQL Server 必須用 GO 分批；同 batch 內 ADD 後立刻 UPDATE/CONSTRAINT 新欄位會解析失敗
-- （Invalid column name 'param_group'）
ALTER TABLE system_parameters ADD param_group VARCHAR(50) NOT NULL DEFAULT 'DEFAULT';
ALTER TABLE system_parameters ADD enabled BIT NOT NULL DEFAULT 1;
ALTER TABLE system_parameters ADD created_at DATETIME2 NOT NULL DEFAULT GETDATE();
GO

UPDATE system_parameters SET param_group = 'OTP' WHERE param_key LIKE 'otp.%';
UPDATE system_parameters SET param_key = 'expiry.minutes' WHERE param_key = 'otp.expiry.minutes';
UPDATE system_parameters SET param_key = 'max.retry' WHERE param_key = 'otp.max.retry';
GO

ALTER TABLE system_parameters DROP CONSTRAINT uk_system_parameters_key;
ALTER TABLE system_parameters ADD CONSTRAINT uk_system_parameters_group_key UNIQUE (param_group, param_key);
GO

CREATE INDEX idx_system_parameters_group ON system_parameters(param_group);
GO
