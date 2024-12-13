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


-- Yeni izinleri küçük harflerle ekle
INSERT INTO permission (name, description, is_hidden, status)
VALUES
    ('subscription:detail', 'Abonelik detaylarını görme izni', false, 'ACTIVE'),
    ('subscription:list', 'Tüm abonelikleri listeleme izni', false, 'ACTIVE'),
    ('subscription:create', 'Yeni bir abonelik oluşturma izni', false, 'ACTIVE'),
    ('subscription:update', 'Abonelik bilgilerini güncelleme izni', false, 'ACTIVE'),
    ('subscription:delete', 'Abonelik silme izni', false, 'ACTIVE'),
    ('company:detail', 'Şirket detaylarını görme izni', false, 'ACTIVE'),
    ('company:list', 'Şirketleri listeleme izni', false, 'ACTIVE'),
    ('company:create', 'Yeni bir şirket oluşturma izni', false, 'ACTIVE'),
    ('company:update', 'Şirket bilgilerini güncelleme izni', false, 'ACTIVE'),
    ('company:delete', 'Şirket silme izni', false, 'ACTIVE');


-- ADMIN rolüne yeni izinlerin atanması
INSERT INTO role_permission (role_id, permission_id)
SELECT 6, id FROM permission WHERE name IN
                                   ('subscription:detail', 'subscription:list', 'subscription:create', 'subscription:update', 'subscription:delete',
                                    'company:detail', 'company:list', 'company:create', 'company:update', 'company:delete');

-- COMPANY_OWNER rolüne yeni izinlerin atanması
INSERT INTO role_permission (role_id, permission_id)
SELECT 8, id FROM permission WHERE name IN
                                   ('company:detail', 'company:list', 'company:create', 'company:update', 'company:delete');



INSERT INTO subscription_plan (status, description, max_proje, max_task, max_user, price, features)
VALUES
    ('FREE', 'Temel özelliklere sahip plan', 1, 10, 5, 0.00, 'Sadece temel proje, görev ve kullanıcı desteği'),
    ('PREMIUM', 'Gelişmiş özellikler ve sınırsız proje desteği', 10, 100, 50, 149.99, 'Sınırsız proje, 100 görev, 50 kullanıcı desteği'),
    ('ENTERPRISE', 'Kurumsal seviyede tüm özellikler', NULL, NULL, NULL, 499.99, 'Sınırsız proje, görev ve kullanıcı desteği');


-- Insert a user into 'user' table (assuming role_id 1 is for USER)
INSERT INTO user (email, first_name, last_name, password, phone, photo, role_id, status) VALUES
                                                                                             ('sedef@example.com', 'Sedef', 'Nuray', 'password123', 5551234567, 'photo.jpg', 1, 'NOT_VERIFIED'),
                                                                                             ('admin@example.com', 'Admin', 'User', 'securepassword', 5559876543, 'admin_photo.jpg', 6, 'VERIFIED'),
                                                                                             ('jane.doe@example.com', 'Jane', 'Doe', 'janepassword', 5551112222, 'jane_photo.jpg', 2, 'VERIFIED');
INSERT INTO address (id, street, city, zipcode, country) VALUES
                                                             (5, '123 Main St', 'Metropolis', '12345', 'USA'),
                                                             (2, '456 Elm St', 'Gotham', '67890', 'USA'),
                                                             (3, '789 Oak St', 'Star City', '11223', 'Canada');

-- Insert into ContactInfo table
INSERT INTO contact_info (id, phone_number, email, website, social_media) VALUES
                                                                            (1, 1234567890, 'info@techinnovations.com', 'www.techinnovations.com', '@techinnovations'),
                                                                            (2, 9876543210, 'contact@elmcorp.com', 'www.elmcorp.com', '@elmcorp'),
                                                                            (3, 5551234567, 'hello@oakindustries.ca', 'www.oakindustries.ca', '@oakindustries');

INSERT INTO company (id, name, description, tax_number, address_id, contact_info, status, subscription_plan_status) VALUES
                                                                                                                (1, 'Tech Innovations', 'Leading technology company', 123456789, 5, 1, 'ACTIVE', 'PREMIUM'),
                                                                                                                (2, 'Elm Corporation', 'Innovative solutions for everyday life', 987654321, 2, 2, 'ACTIVE', 'FREE'),
                                                                                                                (3, 'Oak Industries', 'Pioneering industrial excellence', 555123456, 3, 3, 'INACTIVE', 'FREE');