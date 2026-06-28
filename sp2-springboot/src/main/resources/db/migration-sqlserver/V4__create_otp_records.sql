-- V4: OTP records for identity verification (SQL Server)
CREATE TABLE otp_records (
    id                  BIGINT IDENTITY(1,1) PRIMARY KEY,
    user_id             BIGINT         NOT NULL,
    otp_code            VARCHAR(10)    NOT NULL,
    purpose             VARCHAR(30)    NOT NULL,
    expired_at          DATETIME2      NOT NULL,
    verified            BIT            NOT NULL DEFAULT 0,
    retry_count         INT            NOT NULL DEFAULT 0,
    created_at          DATETIME2      NOT NULL DEFAULT GETDATE(),
    CONSTRAINT fk_otp_records_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE INDEX idx_otp_records_user_purpose ON otp_records(user_id, purpose);
CREATE INDEX idx_otp_records_expired_at ON otp_records(expired_at);
