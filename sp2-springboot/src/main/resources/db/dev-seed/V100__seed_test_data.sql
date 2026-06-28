-- V100: Development seed data (dev profile only via db/dev-seed location)
-- Default password for all test users: Password123!

INSERT INTO users (user_id, username, password, email, full_name, mobile, national_id, status, created_at, updated_at)
VALUES
    ('USR-SEED0001', 'admin', '$2b$10$EWu4jpysblxqMHy69Gc0MOWQwB9YD9a7e/fKsHsxdsLAUvlkak8oC', 'admin@tlbank.local', 'System Admin', '0912000001', 'A100000001', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('USR-SEED0002', 'reviewer1', '$2b$10$EWu4jpysblxqMHy69Gc0MOWQwB9YD9a7e/fKsHsxdsLAUvlkak8oC', 'reviewer1@tlbank.local', 'Review Officer', '0912000002', 'A100000002', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('USR-SEED0003', 'applicant1', '$2b$10$EWu4jpysblxqMHy69Gc0MOWQwB9YD9a7e/fKsHsxdsLAUvlkak8oC', 'applicant1@example.com', 'Test Applicant', '0912345678', 'A123456789', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO user_roles (user_id, role, created_at)
VALUES
    (1, 'ADMIN', CURRENT_TIMESTAMP),
    (2, 'REVIEWER', CURRENT_TIMESTAMP),
    (3, 'APPLICANT', CURRENT_TIMESTAMP);

INSERT INTO card_products (product_code, product_name, description, annual_fee, credit_limit_min, credit_limit_max, status, created_at, updated_at)
VALUES
    ('TL-CLASSIC', 'TL Classic Card', 'Entry-level credit card with no annual fee for the first year.', 0, 10000, 100000, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('TL-PREMIUM', 'TL Premium Card', 'Premium rewards card with airport lounge access.', 3000, 50000, 500000, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO product_features (product_id, feature_name, feature_description, created_at)
VALUES
    (1, 'Cashback', '1% cashback on all purchases', CURRENT_TIMESTAMP),
    (1, 'Contactless', 'Tap-to-pay enabled', CURRENT_TIMESTAMP),
    (2, 'Lounge Access', '2 airport lounge visits per year', CURRENT_TIMESTAMP),
    (2, 'Travel Insurance', 'Complimentary travel insurance coverage', CURRENT_TIMESTAMP);

INSERT INTO system_parameters (param_group, param_key, param_value, description, enabled, created_at, updated_at)
VALUES
    ('OTP', 'expire_minutes', '5', 'OTP validity period in minutes', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('OTP', 'max_retry', '3', 'Maximum OTP verification attempts', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('UPLOAD', 'max.size.mb', '10', 'Maximum document upload size in MB', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('CACHE', 'ttl_seconds', '21600', 'Default cache TTL in seconds (6 hours)', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
