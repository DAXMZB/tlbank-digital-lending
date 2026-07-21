-- V12: Extend otp_records for Sprint 6 mobile-based OTP verification (SQL Server)
-- 注意：SQL Server 必須用 GO 分批；同 batch 內 ADD 後立刻 UPDATE/INDEX 新欄位會解析失敗
ALTER TABLE otp_records ALTER COLUMN user_id BIGINT NULL;
ALTER TABLE otp_records ADD mobile VARCHAR(20) NULL;
ALTER TABLE otp_records ADD status VARCHAR(20) NOT NULL DEFAULT 'PENDING';
ALTER TABLE otp_records ADD verified_at DATETIME2 NULL;
GO

UPDATE otp_records SET status = 'VERIFIED' WHERE verified = 1;
UPDATE otp_records SET status = 'PENDING' WHERE verified = 0;
GO

-- V4 以「verified BIT NOT NULL DEFAULT 0」建立欄位時，SQL Server 會自動產生未命名的 DEFAULT constraint。
-- Error 4922：該 DEFAULT 仍綁定欄位，必須先 DROP CONSTRAINT 才能 DROP COLUMN。
-- Constraint 名稱為系統自動產生（非 repository 固定 DF_ 名稱），因此自 catalog 解析後再刪除。
DECLARE @df_verified sysname;
SELECT @df_verified = dc.name
FROM sys.default_constraints AS dc
INNER JOIN sys.columns AS c
    ON c.object_id = dc.parent_object_id
   AND c.column_id = dc.parent_column_id
WHERE dc.parent_object_id = OBJECT_ID(N'dbo.otp_records')
  AND c.name = N'verified';

IF @df_verified IS NOT NULL
BEGIN
    EXEC(N'ALTER TABLE dbo.otp_records DROP CONSTRAINT ' + QUOTENAME(@df_verified));
END
GO

ALTER TABLE otp_records DROP COLUMN verified;
GO

CREATE INDEX idx_otp_records_mobile_status ON otp_records(mobile, status);
GO
