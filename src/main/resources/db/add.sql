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
    ('company-user:detail', 'Şirket kullanıcısının detaylarını görme izni', false, 'ACTIVE'),
    ('company-user:list', 'Şirket kullanıcılarını listeleme izni', false, 'ACTIVE'),
    ('company-user:create', 'Şirket kullanıcısı oluşturma izni', false, 'ACTIVE'),
    ('company-user:update', 'Şirket kullanıcısı güncelleme izni', false, 'ACTIVE'),
    ('company-user:delete', 'Şirket kullanıcısını silme izni', false, 'ACTIVE'),
    ('address:create', 'Adres oluşturma izni', false, 'ACTIVE'),
    ('address:delete', 'Adres silme izni', false, 'ACTIVE'),
    ('address:update', 'Adres güncelleme izni', false, 'ACTIVE'),
    ('address:list', 'Adresleri listeleme izni', false, 'ACTIVE'),
    ('address:detail', 'Adres detaylarını görme izni', false, 'ACTIVE'),
    ('user:detail', 'Kullanıcı detaylarını görme izni', false, 'ACTIVE'),
    ('user:list', 'Kullanıcıları listeleme izni', false, 'ACTIVE'),
    ('user:create', 'Yeni bir kullanıcı oluşturma izni', false, 'ACTIVE'),
    ('user:update', 'Kullanıcı bilgilerini güncelleme izni', false, 'ACTIVE'),
    ('user:delete', 'Kullanıcı silme izni', false, 'ACTIVE'),
    ('project:create', 'Yeni proje oluşturma izni', false, 'ACTIVE'),
    ('project:update', 'Proje bilgilerini güncelleme izni', false, 'ACTIVE'),
    ('project:delete', 'Proje silme izni', false, 'ACTIVE'),
    ('project:detail', 'Proje detaylarını görme izni', false, 'ACTIVE'),
    ('group:create', 'Yeni grup oluşturma izni', false, 'ACTIVE'),
    ('group:update', 'Grup bilgilerini güncelleme izni', false, 'ACTIVE'),
    ('group:delete', 'Grup silme izni', false, 'ACTIVE'),
    ('group:detail', 'Grup detaylarını görme izni', false, 'ACTIVE'),
    ('subgroup:create', 'Yeni alt grup oluşturma izni', false, 'ACTIVE'),
    ('subgroup:update', 'Alt grup bilgilerini güncelleme izni', false, 'ACTIVE'),
    ('subgroup:delete', 'Alt grup silme izni', false, 'ACTIVE'),
    ('subgroup:detail', 'Sub-grup detaylarını görme izni', false, 'ACTIVE'),
    ('project-status:list', 'Proje durumlarını listeleme izni', false, 'ACTIVE'),
    ('project-user:delete', 'Proje kullanıcısını silme izni', false, 'ACTIVE'),
    ('project-user:detail', 'Proje kullanıcısının detaylarını görme izni', false, 'ACTIVE'),
    ('project-user:create', 'Proje kullanıcısı oluşturma izni', false, 'ACTIVE'),
    ('project-user:update', 'Proje kullanıcısını güncelleme izni', false, 'ACTIVE'),
    ('company-label:create', 'Şirkete etiket ekleme izni', false, 'ACTIVE'),
    ('company-label:delete', 'Şirketteki etiketi silme izni', false, 'ACTIVE'),
    ('label:create', 'Yeni etiket oluşturma izni', false, 'ACTIVE'),
    ('label:detail', 'Etiket detaylarını görme izni', false, 'ACTIVE'),
    ('label:delete', 'Etiket silme izni', false, 'ACTIVE');


-- ADMIN rolüne yeni izinlerin atanması
INSERT INTO role_permission (role_id, permission_id)
SELECT 6, id FROM permission;


-- COMPANY_OWNER rolüne yeni izinlerin atanması
INSERT INTO role_permission (role_id, permission_id)
SELECT 8, id
FROM permission
WHERE name IN
      ('company:detail', 'company:list', 'company:addUserToCompany', 'company:update', 'company:delete',
       'address:addUserToCompany', 'address:delete', 'address:update', 'address:list', 'address:detail',
       'group:addUserToCompany', 'group:list', 'group:detail', 'group:update', 'group:delete',
       'subgroup:addUserToCompany', 'subgroup:list', 'subgroup:detail', 'subgroup:update', 'subgroup:delete',
       'company-user:detail', 'company-user:list', 'company-user:addUserToCompany', 'company-user:update', 'company-user:delete');


INSERT INTO subscription_plan (status, description, max_proje, max_task, max_user, price, features)
VALUES
    ('FREE', 'Temel özelliklere sahip plan', 1, 10, 5, 0.00, 'Sadece temel proje, görev ve kullanıcı desteği'),
    ('PREMIUM', 'Gelişmiş özellikler ve sınırsız proje desteği', 10, 100, 50, 149.99, 'Sınırsız proje, 100 görev, 50 kullanıcı desteği'),
    ('ENTERPRISE', 'Kurumsal seviyede tüm özellikler', NULL, NULL, NULL, 499.99, 'Sınırsız proje, görev ve kullanıcı desteği');


-- Kullanıcıları eklemek için INSERT INTO komutları
INSERT INTO user (email, first_name, last_name, password, phone, photo, status, role_id, last_login_time, created_at, updated_at)
VALUES
    ('admin@example.com', 'Admin', 'User', 'adminpassword', 1234567890, 'adminphoto.jpg', 'VERIFIED', 8, NOW(), NOW(), NOW()),  -- Admin
    ('companyowner@example.com', 'Company', 'Owner', 'ownerpassword', 2345678901, 'ownerphoto.jpg', 'VERIFIED', 6, NOW(), NOW(), NOW()),  -- Company Owner
    ('user1@example.com', 'User', 'One', 'user1password', 3456789012, 'user1photo.jpg', 'VERIFIED', 1, NOW(), NOW(), NOW()),  -- User 1
    ('user2@example.com', 'User', 'Two', 'user2password', 4567890123, 'user2photo.jpg', 'VERIFIED', 1, NOW(), NOW(), NOW()),  -- User 2
    ('user3@example.com', 'User', 'Three', 'user3password', 5678901234, 'user3photo.jpg', 'VERIFIED', 1, NOW(), NOW(), NOW()),  -- User 3
    ('user4@example.com', 'User', 'Four', 'user4password', 6789012345, 'user4photo.jpg', 'VERIFIED', 1, NOW(), NOW(), NOW()),  -- User 4
    ('user5@example.com', 'User', 'Five', 'user5password', 7890123456, 'user5photo.jpg', 'VERIFIED', 1, NOW(), NOW(), NOW()),  -- User 5
    ('user6@example.com', 'User', 'Six', 'user6password', 8901234567, 'user6photo.jpg', 'VERIFIED', 1, NOW(), NOW(), NOW()),  -- User 6
    ('user7@example.com', 'User', 'Seven', 'user7password', 9012345678, 'user7photo.jpg', 'VERIFIED', 1, NOW(), NOW(), NOW()),  -- User 7
    ('user8@example.com', 'User', 'Eight', 'user8password', 1234987650, 'user8photo.jpg', 'VERIFIED', 1, NOW(), NOW(), NOW());  -- User 8

-- Kullanıcı doğrulama tablolarını güncellemek için
INSERT INTO user_verification (user_id, status, type)
VALUES
    (1, 'COMPLETED', 'EMAIL'),  -- Admin
    (2, 'COMPLETED', 'EMAIL'),  -- Company Owner
    (3, 'COMPLETED', 'EMAIL'),  -- User 1
    (4, 'COMPLETED', 'EMAIL'),  -- User 2
    (5, 'COMPLETED', 'EMAIL'),  -- User 3
    (6, 'COMPLETED', 'EMAIL'),  -- User 4
    (7, 'COMPLETED', 'EMAIL'),  -- User 5
    (8, 'COMPLETED', 'EMAIL'),  -- User 6
    (9, 'COMPLETED', 'EMAIL'),  -- User 7
    (10, 'COMPLETED', 'EMAIL'); -- User 8

-- Şirketleri oluşturma
INSERT INTO companies (name, description, phone_number, email, website, status, subscription_plan_id)
VALUES
    ('Tech Innovations', 'A leading tech company specializing in innovative solutions.', 1234567890, 'techinnovations@example.com', 'https://techinnovations.com', 'ACTIVE', 1),  -- Şirket 1
    ('Creative Solutions', 'A creative company focused on design and development.', 2345678901, 'creativesolutions@example.com', 'https://creativesolutions.com', 'ACTIVE', 2);  -- Şirket 2

INSERT INTO company_owners (company_id, user_id)
VALUES
    (1, 2),  -- Tech Innovations şirketine sahip olan kullanıcı (User 8)
    (2, 2);  -- Creative Solutions şirketine sahip olan kullanıcı (User 7)

-- Kullanıcıları rastgele şirketlere atama (Her kullanıcıya bir şirket)
INSERT INTO company_users (company_id, user_id, start_date)
VALUES
    (1, 3, '2025-01-28'),  -- User 1, Tech Innovations
    (1, 4, '2025-01-28'),  -- User 2, Tech Innovations
    (1, 5, '2025-01-28'),  -- User 3, Tech Innovations
    (1, 6, '2025-01-28'),  -- User 4, Tech Innovations
    (1, 7, '2025-01-28'),  -- User 5, Tech Innovations
    (2, 8, '2025-01-28'),  -- User 6, Creative Solutions
    (2, 9, '2025-01-28'),  -- User 7, Creative Solutions
    (2, 10, '2025-01-28'), -- User 8, Creative Solutions
    (2, 1, '2025-01-28'),  -- Admin, Creative Solutions
    (2, 2, '2025-01-28');  -- Company Owner, Creative Solutions


-- Grupları ekleme
INSERT INTO bands (name, company_id)
VALUES
-- Tech Innovations (company_id = 1)
('Yazılım Geliştirme', 1),
('Pazarlama', 1),
('Destek', 1),
('Tasarım', 1),
('Satış', 1),
('İnsan Kaynakları', 1),

-- Creative Solutions (company_id = 2)
('Yazılım Geliştirme', 2),
('Pazarlama', 2),
('Tasarım', 2),
('Satış', 2),
('İnsan Kaynakları', 2);

-- Alt Grupları ekleme
-- Tech Innovations
-- Yazılım Geliştirme (group_id = 1)
INSERT INTO sub_bands (name, group_id)
VALUES
    ('Frontend', 1),
    ('Backend', 1),
    ('Fullstack', 1),
    ('Android', 1),
    ('iOS', 1),
    ('DevOps', 1);

-- Pazarlama (group_id = 2)
INSERT INTO sub_bands (name, group_id)
VALUES
    ('Dijital Pazarlama', 2),
    ('SEO', 2),
    ('İçerik Pazarlama', 2),
    ('Sosyal Medya', 2);

-- Destek (group_id = 3)
INSERT INTO sub_bands (name, group_id)
VALUES
    ('Müşteri Hizmetleri', 3),
    ('Teknik Destek', 3);

-- Tasarım (group_id = 4)
INSERT INTO sub_bands (name, group_id)
VALUES
    ('UI/UX Tasarım', 4),
    ('Grafik Tasarım', 4);

-- Satış (group_id = 5)
INSERT INTO sub_bands (name, group_id)
VALUES
    ('Satış Temsilcileri', 5),
    ('İş Geliştirme', 5);

-- İnsan Kaynakları (group_id = 6)
INSERT INTO sub_bands (name, group_id)
VALUES
    ('İşe Alım', 6),
    ('Eğitim & Gelişim', 6),
    ('Çalışan Deneyimi', 6);

-- Creative Solutions
-- Yazılım Geliştirme (group_id = 7)
INSERT INTO sub_bands (name, group_id)
VALUES
    ('Frontend', 7),
    ('Backend', 7),
    ('DevOps', 7);

-- Pazarlama (group_id = 8)
INSERT INTO sub_bands (name, group_id)
VALUES
    ('Dijital Pazarlama', 8),
    ('SEO', 8);

-- Tasarım (group_id = 9)
INSERT INTO sub_bands (name, group_id)
VALUES
    ('UI/UX Tasarım', 9),
    ('Grafik Tasarım', 9);

-- Satış (group_id = 10)
INSERT INTO sub_bands (name, group_id)
VALUES
    ('Satış Temsilcileri', 10),
    ('İş Geliştirme', 10);

-- İnsan Kaynakları (group_id = 11)
INSERT INTO sub_bands (name, group_id)
VALUES
    ('İşe Alım', 11),
    ('Eğitim & Gelişim', 11);
