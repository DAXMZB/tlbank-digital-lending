-- V11: Extend schema for Sprint 5 credit card application domain model (SQL Server)
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

UPDATE applications SET status = 'INIT' WHERE status = 'DRAFT';

ALTER TABLE workflow_histories ADD operator VARCHAR(100) NULL;

ALTER TABLE application_documents ADD file_size BIGINT NOT NULL DEFAULT 0;

ALTER TABLE card_products ADD card_type VARCHAR(20) NOT NULL DEFAULT 'VISA';
ALTER TABLE card_products ADD enabled BIT NOT NULL DEFAULT 1;

UPDATE card_products SET enabled = 1 WHERE status = 'ACTIVE';

CREATE INDEX idx_applications_applicant_mobile ON applications(applicant_mobile);
