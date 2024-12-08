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
    ('SUBSCRIPTION_FIND_BY_ID', 'Abonelik detaylarını görme izni', false, 'ACTIVE'),
    ('SUBSCRIPTION_FIND_ALL', 'Tüm abonelikleri listeleme izni', false, 'ACTIVE'),
    ('SUBSCRIPTION_CREATE', 'Yeni bir abonelik oluşturma izni', false, 'ACTIVE'),
    ('SUBSCRIPTION_UPDATE', 'Abonelik bilgilerini güncelleme izni', false, 'ACTIVE'),
    ('SUBSCRIPTION_DELETE', 'Abonelik silme izni', false, 'ACTIVE');

-- Insert roles and permissions into 'role_permission' table (many-to-many relationship)
-- ADMIN rolüne yeni izinlerin atanması
INSERT INTO role_permission (role_id, permission_id)
VALUES
    (6, (SELECT id FROM permission WHERE name = 'SUBSCRIPTION_FIND_BY_ID')), -- FIND_BY_ID
    (6, (SELECT id FROM permission WHERE name = 'SUBSCRIPTION_FIND_ALL')),   -- FIND_ALL
    (6, (SELECT id FROM permission WHERE name = 'SUBSCRIPTION_CREATE')),    -- CREATE
    (6, (SELECT id FROM permission WHERE name = 'SUBSCRIPTION_UPDATE')),    -- UPDATE
    (6, (SELECT id FROM permission WHERE name = 'SUBSCRIPTION_DELETE'));    -- DELETE


INSERT INTO subscription (status, description, max_proje, max_task, max_user, price, features)
VALUES
    ('FREE', 'Temel özelliklere sahip plan', 1, 10, 5, 0.00, 'Sadece temel proje, görev ve kullanıcı desteği'),
    ('PREMIUM', 'Gelişmiş özellikler ve sınırsız proje desteği', 10, 100, 50, 149.99, 'Sınırsız proje, 100 görev, 50 kullanıcı desteği'),
    ('ENTERPRISE', 'Kurumsal seviyede tüm özellikler', NULL, NULL, NULL, 499.99, 'Sınırsız proje, görev ve kullanıcı desteği');


-- Insert a user into 'user' table (assuming role_id 1 is for USER)
INSERT INTO user (email, first_name, last_name, password, phone, photo, role_id, status)
VALUES
    ('sedef@example.com', 'Sedef', 'Nuray', 'password123', 5551234567, 'photo.jpg', 1, 'NOT_VERIFIED');

-- Yeni bir ADMIN rolüne sahip kullanıcı ekleniyor
INSERT INTO user (email, first_name, last_name, password, phone, photo, role_id, status)
VALUES
    ('admin@example.com', 'Admin', 'User', 'securepassword', 5559876543, 'admin_photo.jpg', 6, 'VERIFIED');
