-- V12: Extend otp_records for Sprint 6 mobile-based OTP verification (SQL Server)
ALTER TABLE otp_records ALTER COLUMN user_id BIGINT NULL;
ALTER TABLE otp_records ADD mobile VARCHAR(20) NULL;
ALTER TABLE otp_records ADD status VARCHAR(20) NOT NULL DEFAULT 'PENDING';
ALTER TABLE otp_records ADD verified_at DATETIME2 NULL;

UPDATE otp_records SET status = 'VERIFIED' WHERE verified = 1;
UPDATE otp_records SET status = 'PENDING' WHERE verified = 0;

ALTER TABLE otp_records DROP COLUMN verified;

CREATE INDEX idx_otp_records_mobile_status ON otp_records(mobile, status);
