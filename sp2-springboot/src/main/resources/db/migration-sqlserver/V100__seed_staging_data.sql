-- V100: Staging/Docker seed data
-- Default password for all accounts: Password@123

INSERT INTO users (user_id, username, password, email, full_name, mobile, national_id, status, created_at, updated_at)
VALUES
    ('USR-00000001', 'admin', '$2a$12$YD0OCMIIpcPOiiZJuDA1a.hxu0imr2egn9fbK718ZozmqXO08p4t.', 'admin@tlbank.local', 'System Admin', '0912000001', 'A100000001', 'ACTIVE', GETDATE(), GETDATE()),
    ('USR-00000002', 'reviewer', '$2a$12$YD0OCMIIpcPOiiZJuDA1a.hxu0imr2egn9fbK718ZozmqXO08p4t.', 'reviewer@tlbank.local', 'Review Officer', '0912000002', 'A100000002', 'ACTIVE', GETDATE(), GETDATE()),
    ('USR-00000003', 'user01', '$2a$12$YD0OCMIIpcPOiiZJuDA1a.hxu0imr2egn9fbK718ZozmqXO08p4t.', 'user01@example.com', 'Test Applicant', '0912345678', 'A123456789', 'ACTIVE', GETDATE(), GETDATE());

INSERT INTO user_roles (user_id, role, created_at)
VALUES
    (1, 'ADMIN', GETDATE()),
    (2, 'REVIEWER', GETDATE()),
    (3, 'USER', GETDATE());

INSERT INTO card_products (product_code, product_name, description, annual_fee, credit_limit_min, credit_limit_max, status, card_type, enabled, created_at, updated_at)
VALUES
    ('TL-CLASSIC', 'TL Classic Card', 'Entry-level credit card with no annual fee for the first year.', 0, 10000, 100000, 'ACTIVE', 'VISA', 1, GETDATE(), GETDATE()),
    ('TL-PREMIUM', 'TL Premium Card', 'Premium rewards card with airport lounge access.', 3000, 50000, 500000, 'ACTIVE', 'VISA', 1, GETDATE(), GETDATE());

INSERT INTO product_features (product_id, feature_name, feature_description, created_at)
VALUES
    (1, 'Cashback', '1% cashback on all purchases', GETDATE()),
    (1, 'Contactless', 'Tap-to-pay enabled', GETDATE()),
    (2, 'Lounge Access', '2 airport lounge visits per year', GETDATE()),
    (2, 'Travel Insurance', 'Complimentary travel insurance coverage', GETDATE());

INSERT INTO system_parameters (param_group, param_key, param_value, description, enabled, created_at, updated_at)
VALUES
    ('OTP', 'expire_minutes', '5', 'OTP validity period in minutes', 1, GETDATE(), GETDATE()),
    ('OTP', 'max_retry', '3', 'Maximum OTP verification attempts', 1, GETDATE(), GETDATE()),
    ('UPLOAD', 'max.size.mb', '10', 'Maximum document upload size in MB', 1, GETDATE(), GETDATE()),
    ('CACHE', 'ttl_seconds', '21600', 'Default cache TTL in seconds (6 hours)', 1, GETDATE(), GETDATE());
