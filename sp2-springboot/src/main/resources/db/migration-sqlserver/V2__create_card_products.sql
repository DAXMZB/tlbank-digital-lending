-- V2: Card products and features (SQL Server)
CREATE TABLE card_products (
    id                  BIGINT IDENTITY(1,1) PRIMARY KEY,
    product_code        VARCHAR(30)    NOT NULL,
    product_name        VARCHAR(100)   NOT NULL,
    description         VARCHAR(500),
    annual_fee          DECIMAL(12, 2) NOT NULL DEFAULT 0,
    credit_limit_min    DECIMAL(12, 2) NOT NULL,
    credit_limit_max    DECIMAL(12, 2) NOT NULL,
    status              VARCHAR(20)    NOT NULL DEFAULT 'ACTIVE',
    created_at          DATETIME2      NOT NULL DEFAULT GETDATE(),
    updated_at          DATETIME2      NOT NULL DEFAULT GETDATE(),
    CONSTRAINT uk_card_products_code UNIQUE (product_code)
);

CREATE TABLE product_features (
    id                  BIGINT IDENTITY(1,1) PRIMARY KEY,
    product_id          BIGINT         NOT NULL,
    feature_name        VARCHAR(100)   NOT NULL,
    feature_description VARCHAR(500),
    created_at          DATETIME2      NOT NULL DEFAULT GETDATE(),
    CONSTRAINT fk_product_features_product FOREIGN KEY (product_id) REFERENCES card_products(id)
);

CREATE INDEX idx_card_products_status ON card_products(status);
CREATE INDEX idx_product_features_product_id ON product_features(product_id);
