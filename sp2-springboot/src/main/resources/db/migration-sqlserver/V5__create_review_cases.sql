-- V5: Review cases and remarks (SQL Server)
CREATE TABLE review_cases (
    id                  BIGINT IDENTITY(1,1) PRIMARY KEY,
    application_id      BIGINT         NOT NULL,
    reviewer_id         BIGINT,
    status              VARCHAR(30)    NOT NULL DEFAULT 'PENDING',
    assigned_at         DATETIME2,
    completed_at        DATETIME2,
    created_at          DATETIME2      NOT NULL DEFAULT GETDATE(),
    updated_at          DATETIME2      NOT NULL DEFAULT GETDATE(),
    CONSTRAINT uk_review_cases_application UNIQUE (application_id),
    CONSTRAINT fk_review_cases_application FOREIGN KEY (application_id) REFERENCES applications(id),
    CONSTRAINT fk_review_cases_reviewer FOREIGN KEY (reviewer_id) REFERENCES users(id)
);

CREATE TABLE review_remarks (
    id                  BIGINT IDENTITY(1,1) PRIMARY KEY,
    review_case_id      BIGINT         NOT NULL,
    remark              VARCHAR(1000)  NOT NULL,
    created_by          BIGINT         NOT NULL,
    created_at          DATETIME2      NOT NULL DEFAULT GETDATE(),
    CONSTRAINT fk_review_remarks_case FOREIGN KEY (review_case_id) REFERENCES review_cases(id),
    CONSTRAINT fk_review_remarks_user FOREIGN KEY (created_by) REFERENCES users(id)
);

CREATE INDEX idx_review_cases_status ON review_cases(status);
CREATE INDEX idx_review_cases_reviewer_id ON review_cases(reviewer_id);
CREATE INDEX idx_review_remarks_review_case_id ON review_remarks(review_case_id);
