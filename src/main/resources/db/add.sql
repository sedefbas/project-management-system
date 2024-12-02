-- Insert roles into 'role' table
INSERT INTO role (name, description, status)
VALUES
    ('USER', 'Sistemi temel düzeyde kullanabilen kullanıcı', 'ACTIVE'),
    ('PROJECT_MANAGER', 'Projeleri yönetme yetkisine sahip kişi', 'ACTIVE'),
    ('DEVELOPER', 'Geliştirme yapan kişi (kod yazan)', 'ACTIVE'),
    ('TESTER', 'Test süreçlerini yürüten kişi', 'ACTIVE'),
    ('SCRUM_MASTER', 'Scrum süreçlerini yöneten kişi', 'ACTIVE'),
    ('ADMIN', 'Sistem yöneticisi, tüm yetkilere sahip', 'ACTIVE'),
    ('VIEWER', 'Yalnızca okuma yetkisi olan kullanıcı', 'ACTIVE'),
    ('COMPANY_OWNER', 'Şirket sahibi', 'ACTIVE'),
    ('MEMBER', 'Yalnızca okuma yetkisi olan kullanıcı', 'ACTIVE');

-- Insert permissions into 'permission' table
INSERT INTO permission (name, description, is_hidden, status)
VALUES
    ('READ', 'Okuma izni', false, 'ACTIVE'),
    ('WRITE', 'Yazma izni', false, 'ACTIVE'),
    ('DELETE', 'Silme izni', false, 'ACTIVE'),
    ('UPDATE', 'Güncelleme izni', false, 'ACTIVE'),
    ('MANAGE_PROJECTS', 'Projeleri yönetme izni', false, 'ACTIVE'),
    ('MANAGE_USERS', 'Kullanıcıları yönetme izni', false, 'ACTIVE');

-- Insert roles and permissions into 'role_permission' table (many-to-many relationship)
INSERT INTO role_permission (role_id, permission_id)
VALUES
    (1, 1), -- USER: READ
    (1, 2), -- USER: WRITE
    (2, 1), -- PROJECT_MANAGER: READ
    (2, 2), -- PROJECT_MANAGER: WRITE
    (2, 3), -- PROJECT_MANAGER: DELETE
    (3, 1), -- DEVELOPER: READ
    (3, 2), -- DEVELOPER: WRITE
    (3, 4), -- DEVELOPER: UPDATE
    (4, 1), -- TESTER: READ
    (4, 2), -- TESTER: WRITE
    (5, 1), -- SCRUM_MASTER: READ
    (5, 2), -- SCRUM_MASTER: WRITE
    (6, 1), -- ADMIN: READ
    (6, 2), -- ADMIN: WRITE
    (6, 3), -- ADMIN: DELETE
    (6, 4), -- ADMIN: UPDATE
    (7, 1), -- VIEWER: READ
    (8, 1), -- COMPANY_OWNER: READ
    (8, 2), -- COMPANY_OWNER: WRITE
    (9, 1); -- MEMBER: READ

-- Insert a user into 'user' table (assuming role_id 1 is for USER)
INSERT INTO user (email, first_name, last_name, password, phone, photo, role_id, status)
VALUES
    ('sedef@example.com', 'Sedef', 'Nuray', 'password123', 5551234567, 'photo.jpg', 1, 'NOT_VERIFIED');
