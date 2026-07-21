-- V11: Extend schema for Sprint 5 credit card application domain model (SQL Server)
-- 注意：SQL Server 必須用 GO 分批；同 batch 內 ADD 後立刻 UPDATE/INDEX 新欄位會解析失敗
ALTER TABLE applications ALTER COLUMN user_id BIGINT NULL;

ALTER TABLE applications ADD applicant_full_name VARCHAR(100) NULL;
ALTER TABLE applications ADD applicant_national_id VARCHAR(20) NULL;
ALTER TABLE applications ADD applicant_mobile VARCHAR(20) NULL;
ALTER TABLE applications ADD applicant_email VARCHAR(100) NULL;
ALTER TABLE applications ADD applicant_date_of_birth DATE NULL;
ALTER TABLE applications ADD address_city VARCHAR(50) NULL;
ALTER TABLE applications ADD address_district VARCHAR(50) NULL;
ALTER TABLE applications ADD address_street VARCHAR(200) NULL;
ALTER TABLE applications ADD address_zip_code VARCHAR(10) NULL;
GO

UPDATE applications SET status = 'INIT' WHERE status = 'DRAFT';
GO

ALTER TABLE workflow_histories ADD operator VARCHAR(100) NULL;

ALTER TABLE application_documents ADD file_size BIGINT NOT NULL DEFAULT 0;

ALTER TABLE card_products ADD card_type VARCHAR(20) NOT NULL DEFAULT 'VISA';
ALTER TABLE card_products ADD enabled BIT NOT NULL DEFAULT 1;
GO

UPDATE card_products SET enabled = 1 WHERE status = 'ACTIVE';
GO

CREATE INDEX idx_applications_applicant_mobile ON applications(applicant_mobile);
GO
