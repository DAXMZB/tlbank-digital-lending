-- V12: Extend otp_records for Sprint 6 mobile-based OTP verification

ALTER TABLE otp_records ALTER COLUMN user_id SET NULL;
ALTER TABLE otp_records ADD COLUMN mobile VARCHAR(20);
ALTER TABLE otp_records ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'PENDING';
ALTER TABLE otp_records ADD COLUMN verified_at TIMESTAMP;

UPDATE otp_records SET status = 'VERIFIED' WHERE verified = TRUE;
UPDATE otp_records SET status = 'PENDING' WHERE verified = FALSE;

ALTER TABLE otp_records DROP COLUMN verified;

CREATE INDEX idx_otp_records_mobile_status ON otp_records(mobile, status);
