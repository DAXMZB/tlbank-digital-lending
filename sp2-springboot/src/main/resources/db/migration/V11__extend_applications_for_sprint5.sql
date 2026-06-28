-- V11: Extend schema for Sprint 5 credit card application domain model

ALTER TABLE applications ALTER COLUMN user_id SET NULL;
ALTER TABLE applications ALTER COLUMN status SET DEFAULT 'INIT';

ALTER TABLE applications ADD COLUMN applicant_full_name VARCHAR(100);
ALTER TABLE applications ADD COLUMN applicant_national_id VARCHAR(20);
ALTER TABLE applications ADD COLUMN applicant_mobile VARCHAR(20);
ALTER TABLE applications ADD COLUMN applicant_email VARCHAR(100);
ALTER TABLE applications ADD COLUMN applicant_date_of_birth DATE;
ALTER TABLE applications ADD COLUMN address_city VARCHAR(50);
ALTER TABLE applications ADD COLUMN address_district VARCHAR(50);
ALTER TABLE applications ADD COLUMN address_street VARCHAR(200);
ALTER TABLE applications ADD COLUMN address_zip_code VARCHAR(10);

UPDATE applications SET status = 'INIT' WHERE status = 'DRAFT';

ALTER TABLE workflow_histories ADD COLUMN operator VARCHAR(100);

ALTER TABLE application_documents ADD COLUMN file_size BIGINT NOT NULL DEFAULT 0;

ALTER TABLE card_products ADD COLUMN card_type VARCHAR(20) NOT NULL DEFAULT 'VISA';
ALTER TABLE card_products ADD COLUMN enabled BOOLEAN NOT NULL DEFAULT TRUE;

UPDATE card_products SET enabled = TRUE WHERE status = 'ACTIVE';
