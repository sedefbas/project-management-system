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
    ('company:delete', 'Şirket silme izni', false, 'ACTIVE'),
    ('address:create', 'Adres oluşturma izni', false, 'ACTIVE'),
    ('address:delete', 'Adres silme izni', false, 'ACTIVE'),
    ('address:update', 'Adres güncelleme izni', false, 'ACTIVE'),
    ('address:list', 'Adresleri listeleme izni', false, 'ACTIVE'),
    ('address:detail', 'Adres detaylarını görme izni', false, 'ACTIVE');


-- ADMIN rolüne yeni izinlerin atanması
INSERT INTO role_permission (role_id, permission_id)
SELECT 6, id FROM permission WHERE name IN
                                   ('subscription:detail', 'subscription:list', 'subscription:create', 'subscription:update', 'subscription:delete',
                                    'company:detail', 'company:list', 'company:create', 'company:update', 'company:delete',
                                    'address:create', 'address:delete', 'address:update', 'address:list', 'address:detail');

-- COMPANY_OWNER rolüne yeni izinlerin atanması
INSERT INTO role_permission (role_id, permission_id)
SELECT 8, id FROM permission WHERE name IN
                                   ('company:detail', 'company:list', 'company:create', 'company:update', 'company:delete',
                                    'address:create', 'address:delete', 'address:update', 'address:list', 'address:detail');



INSERT INTO subscription_plan (status, description, max_proje, max_task, max_user, price, features)
VALUES
    ('FREE', 'Temel özelliklere sahip plan', 1, 10, 5, 0.00, 'Sadece temel proje, görev ve kullanıcı desteği'),
    ('PREMIUM', 'Gelişmiş özellikler ve sınırsız proje desteği', 10, 100, 50, 149.99, 'Sınırsız proje, 100 görev, 50 kullanıcı desteği'),
    ('ENTERPRISE', 'Kurumsal seviyede tüm özellikler', NULL, NULL, NULL, 499.99, 'Sınırsız proje, görev ve kullanıcı desteği');





INSERT INTO address (id, street, city, zipcode, country) VALUES
                                                             (5, '123 Main St', 'Metropolis', '12345', 'USA'),
                                                             (2, '456 Elm St', 'Gotham', '67890', 'USA'),
                                                             (3, '789 Oak St', 'Star City', '11223', 'Canada');


INSERT INTO company (id, name, description, tax_number, phone_number, email, website, address_id, status, subscription_plan_status) VALUES
                                                                                                                                        (1, 'Tech Innovations', 'Leading technology company', 123456789, 5551234, 'info@techinnovations.com', 'www.techinnovations.com', 5, 'ACTIVE', 'PREMIUM'),
                                                                                                                                        (2, 'Elm Corporation', 'Innovative solutions for everyday life', 987654321, 5554321, 'contact@elmcorporation.com', 'www.elmcorporation.com', 2, 'ACTIVE', 'FREE'),
                                                                                                                                        (3, 'Oak Industries', 'Pioneering industrial excellence', 555123456, 5556789, 'support@oakindustries.com', 'www.oakindustries.com', 3, 'INACTIVE', 'FREE');
