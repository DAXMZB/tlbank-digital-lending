-- V3: Credit card applications, workflow history, and documents (SQL Server)
CREATE TABLE applications (
    id                  BIGINT IDENTITY(1,1) PRIMARY KEY,
    application_no      VARCHAR(30)    NOT NULL,
    user_id             BIGINT         NOT NULL,
    product_id          BIGINT         NOT NULL,
    status              VARCHAR(30)    NOT NULL DEFAULT 'DRAFT',
    requested_limit     DECIMAL(12, 2),
    submitted_at        DATETIME2,
    created_at          DATETIME2      NOT NULL DEFAULT GETDATE(),
    updated_at          DATETIME2      NOT NULL DEFAULT GETDATE(),
    CONSTRAINT uk_applications_no UNIQUE (application_no),
    CONSTRAINT fk_applications_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_applications_product FOREIGN KEY (product_id) REFERENCES card_products(id)
);

CREATE TABLE workflow_histories (
    id                  BIGINT IDENTITY(1,1) PRIMARY KEY,
    application_id      BIGINT         NOT NULL,
    from_status         VARCHAR(30),
    to_status           VARCHAR(30)    NOT NULL,
    action_by           BIGINT,
    comment             VARCHAR(500),
    action_at           DATETIME2      NOT NULL DEFAULT GETDATE(),
    CONSTRAINT fk_workflow_histories_application FOREIGN KEY (application_id) REFERENCES applications(id),
    CONSTRAINT fk_workflow_histories_user FOREIGN KEY (action_by) REFERENCES users(id)
);

CREATE TABLE application_documents (
    id                  BIGINT IDENTITY(1,1) PRIMARY KEY,
    application_id      BIGINT         NOT NULL,
    document_type       VARCHAR(50)    NOT NULL,
    file_name           VARCHAR(255)   NOT NULL,
    file_path           VARCHAR(500)   NOT NULL,
    uploaded_at         DATETIME2      NOT NULL DEFAULT GETDATE(),
    CONSTRAINT fk_application_documents_application FOREIGN KEY (application_id) REFERENCES applications(id)
);

CREATE INDEX idx_applications_user_id ON applications(user_id);
CREATE INDEX idx_applications_status ON applications(status);
CREATE INDEX idx_applications_product_id ON applications(product_id);
CREATE INDEX idx_applications_created_at ON applications(created_at);
CREATE INDEX idx_applications_submitted_at ON applications(submitted_at);
CREATE INDEX idx_workflow_histories_application_id ON workflow_histories(application_id);
CREATE INDEX idx_application_documents_application_id ON application_documents(application_id);
