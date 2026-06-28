-- V13: Extend review_cases and review_remarks for Sprint 8 review module

ALTER TABLE review_cases ALTER COLUMN application_id SET NULL;
ALTER TABLE review_cases ALTER COLUMN reviewer_id SET NULL;

ALTER TABLE review_cases ADD COLUMN review_case_no VARCHAR(30);
ALTER TABLE review_cases ADD COLUMN application_no VARCHAR(30);
ALTER TABLE review_cases ADD COLUMN assigned_to VARCHAR(50);
ALTER TABLE review_cases ADD COLUMN reviewed_at TIMESTAMP;

ALTER TABLE review_cases ADD CONSTRAINT uk_review_cases_no UNIQUE (review_case_no);

ALTER TABLE review_remarks ALTER COLUMN created_by SET NULL;
ALTER TABLE review_remarks ADD COLUMN operator VARCHAR(100);
ALTER TABLE review_remarks ADD COLUMN content VARCHAR(1000);
