-- V13: Extend review_cases and review_remarks for Sprint 8 review module (SQL Server)
ALTER TABLE review_cases ALTER COLUMN application_id BIGINT NULL;
ALTER TABLE review_cases ALTER COLUMN reviewer_id BIGINT NULL;

ALTER TABLE review_cases ADD review_case_no VARCHAR(30) NULL;
ALTER TABLE review_cases ADD application_no VARCHAR(30) NULL;
ALTER TABLE review_cases ADD assigned_to VARCHAR(50) NULL;
ALTER TABLE review_cases ADD reviewed_at DATETIME2 NULL;

ALTER TABLE review_cases ADD CONSTRAINT uk_review_cases_no UNIQUE (review_case_no);

ALTER TABLE review_remarks ALTER COLUMN created_by BIGINT NULL;
ALTER TABLE review_remarks ADD operator VARCHAR(100) NULL;
ALTER TABLE review_remarks ADD content VARCHAR(1000) NULL;

CREATE INDEX idx_review_cases_application_no ON review_cases(application_no);
CREATE INDEX idx_review_cases_assigned_to ON review_cases(assigned_to);
