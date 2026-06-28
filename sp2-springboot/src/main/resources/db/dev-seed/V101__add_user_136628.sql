-- V101: Additional dev user (username: 136628, password: 123)

INSERT INTO users (user_id, username, password, email, full_name, mobile, national_id, status, created_at, updated_at)
VALUES
    ('USR-136628', '136628', '$2b$12$L1bL.8kvdt8xB8m6uFuGg.srkRx4yzNAPfTKE6nZPkSHDeX1OXd9K', '136628@example.com', 'User 136628', '0913662800', 'A136628001', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO user_roles (user_id, role, created_at)
SELECT id, 'APPLICANT', CURRENT_TIMESTAMP FROM users WHERE username = '136628';
