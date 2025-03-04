
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
    ('company-priority:create', 'Şirkete öncelik ekleme izni', false, 'ACTIVE'),
    ('company-priority:delete', 'Şirketteki öncelik silme izni', false, 'ACTIVE'),
    ('priority:create', 'Yeni öncelik oluşturma izni', false, 'ACTIVE'),
    ('priority:detail', 'Öncelik detaylarını görme izni', false, 'ACTIVE'),
    ('priority:delete', 'Öncelik silme izni', false, 'ACTIVE'),
    ('label:create', 'Yeni etiket oluşturma izni', false, 'ACTIVE'),
    ('label:detail', 'Etiket detaylarını görme izni', false, 'ACTIVE'),
    ('label:delete', 'Etiket silme izni', false, 'ACTIVE'),
    ('issue-assignment:delete', 'Görev atamasını silme izni', false, 'ACTIVE'),
    ('issue-assignment:detail', 'Görev atama detaylarını görme izni', false, 'ACTIVE'),
    ('issue-assignment:update', 'Görev atamasını güncelleme izni', false, 'ACTIVE'),
    ('issue-assignment:create', 'Yeni görev ataması oluşturma izni', false, 'ACTIVE'),
    ('issue:create', 'Yeni görev oluşturma izni', false, 'ACTIVE'),
    ('issue:update', 'Görev bilgilerini güncelleme izni', false, 'ACTIVE'),
    ('issue:delete', 'Görev silme izni', false, 'ACTIVE'),
    ('issue:detail', 'Görev detaylarını görme izni', false, 'ACTIVE'),
    ('issue:list', 'Görevleri listeleme izni', false, 'ACTIVE'),
    ('issue:comment', 'Göreve yorum yapma izni', false, 'ACTIVE'),
    ('issue:assign', 'Göreve kullanıcı atama izni', false, 'ACTIVE'),
    ('issue:change-stage', 'Görevin aşamasını değiştirme izni', false, 'ACTIVE'),
    ('issue:change-priority', 'Görevin önceliğini değiştirme izni', false, 'ACTIVE'),
    ('issue:add-label', 'Göreve etiket ekleme izni', false, 'ACTIVE'),
    ('issue:remove-label', 'Görevden etiket kaldırma izni', false, 'ACTIVE'),
    ('help:detail', 'Yardım detaylarını görme izni', false, 'ACTIVE'),
    ('help:list', 'Yardım listesini görüntüleme izni', false, 'ACTIVE'),
    ('help:create', 'Yeni yardım dokümanı oluşturma izni', false, 'ACTIVE'),
    ('help:update', 'Yardım dokümanı güncelleme izni', false, 'ACTIVE'),
    ('help:delete', 'Yardım dokümanı silme izni', false, 'ACTIVE'),
    ('help-comment:list', 'Yardım yorumlarını listeleme izni', false, 'ACTIVE'),
    ('help-comment:create', 'Yardım yorumu oluşturma izni', false, 'ACTIVE'),
    ('help-comment:delete', 'Yardım yorumunu silme izni', false, 'ACTIVE'),
    ('help-comment:upvote', 'Yardım yorumuna oylama yapma izni', false, 'ACTIVE'),
    ('help-comment:downvote', 'Yardım yorumuna olumsuz oy verme izni', false, 'ACTIVE');



INSERT INTO `role` (name, description, status)
VALUES
    ('USER', 'Basic system user', 'ACTIVE'),
    ('PROJECT_MANAGER', 'Person with project management authority', 'ACTIVE'),
    ('DEVELOPER', 'Person who develops (writes code)', 'ACTIVE'),
    ('TESTER', 'Person who conducts testing processes', 'ACTIVE'),
    ('SCRUM_MASTER', 'Person who manages Scrum processes', 'ACTIVE'),
    ('ADMIN', 'System administrator with full permissions', 'ACTIVE'),
    ('COMPANY_OWNER', 'Company owner', 'ACTIVE'),
    ('MEMBER', 'User with read-only permissions', 'ACTIVE'),
    ('PAIR_ASSIGNE', 'Developer assigned as a pair', 'ACTIVE'),
    ('QA_ASSIGNE', 'Person assigned to testing tasks', 'ACTIVE');


-- ADMIN rolüne yeni izinlerin atanması
INSERT INTO role_permission (role_id, permission_id)
SELECT 6, id FROM permission;

-- USER rolüne (role_id = 1) izinlerin atanması
INSERT INTO role_permission (role_id, permission_id)
SELECT 1, id
FROM permission
WHERE name IN ('user:detail', 'user:list', 'company:create');




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
-- Kullanıcıları eklemek için INSERT INTO komutları
INSERT INTO users (email, first_name, last_name, password, phone, photo, status, role_id, last_login_time, created_at, updated_at)
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

INSERT INTO projects (name, description, photo, status, start_date, end_date, company_id) VALUES
                                                                                              ('Finans Yönetim Sistemi', 'Şirketin finansal süreçlerini yöneten sistem.', 'finans.jpg', 'IN_PROGRESS', '2024-01-10', '2024-12-31', 1),
                                                                                              ('Müşteri Takip Platformu', 'Müşteri ilişkilerini yönetmek için geliştirilmiş bir sistem.', 'musteri.jpg', 'IN_PROGRESS', '2024-02-15', '2024-11-30', 1),
                                                                                              ('E-Ticaret Entegrasyonu', 'E-ticaret sistemleri ile ERP arasında entegrasyon sağlama projesi.', 'eticaret.jpg', 'IN_PROGRESS', '2024-03-01', '2025-01-15', 2),
                                                                                              ('İçerik Yönetim Sistemi', 'Şirket içi içerik yönetimi ve dokümantasyon platformu.', 'icerik.jpg', 'IN_PROGRESS', '2024-04-20', '2024-10-05', 2);

-- Projeler için kullanıcı ekleme işlemi-- Projeler için kullanıcı ekleme
INSERT INTO project_users (user_id, project_id, role_id)
VALUES
    -- Project 1, Tech Innovations, User 1, User 2
    (3, 1, 1),  -- User 1, Project 1, Role 1
    (4, 1, 1),  -- User 2, Project 1, Role 1

    -- Project 2, Tech Innovations, User 3, User 4
    (5, 2, 1),  -- User 3, Project 2, Role 1
    (6, 2, 1),  -- User 4, Project 2, Role 1

    -- Project 3, Creative Solutions, User 5, User 6
    (7, 3, 1),  -- User 5, Project 3, Role 1
    (8, 3, 1),  -- User 6, Project 3, Role 1

    -- Project 4, Creative Solutions, User 7, User 8
    (9, 4, 1),  -- User 7, Project 4, Role 1
    (10, 4, 1); -- User 8, Project 4, Role 1



INSERT INTO stages (name, context, is_default) VALUES
                                                         ('TODO', 'Görev oluşturuldu, beklemede', true),
                                                         ('IN_PROGRESS', 'Üzerinde çalışılıyor', false),
                                                         ('IN_REVIEW', 'Kod gözden geçiriliyor', false),
                                                         ('DONE', 'Tamamlandı', false),
                                                         ('BLOCKED', 'Engellendi, devam edilemiyor', false),
                                                         ('BACKLOG', 'Gelecekte yapılacaklar', false),
                                                         ('TESTING', 'Test aşamasında', false),
                                                         ('DEPLOYED', 'Canlıya alındı', false);


INSERT INTO priorities (name, photo, is_default) VALUES
                                                     ('Low', 'low-priority.png', TRUE),
                                                     ('Medium', 'medium-priority.png', TRUE),
                                                     ('High', 'high-priority.png', FALSE),
                                                     ('Critical', 'critical-priority.png', FALSE);

INSERT INTO labels (name, photo, is_default) VALUES
                                                 ('Bug', 'bug-icon.png', TRUE),
                                                 ('Feature', 'feature-icon.png', FALSE),
                                                 ('Improvement', 'improvement-icon.png', FALSE),
                                                 ('Documentation', 'documentation-icon.png', TRUE);

-- Grupları ekleme
INSERT INTO bands (name, company_id, color)
VALUES
-- Tech Innovations (company_id = 1)
('Yazılım Geliştirme', 1, '#FF5733'),
('Pazarlama', 1, '#33FF57'),
('Destek', 1, '#3357FF'),
('Tasarım', 1, '#FF33A1'),
('Satış', 1, '#FFD700'),
('İnsan Kaynakları', 1, '#8A2BE2'),

-- Creative Solutions (company_id = 2)
('Yazılım Geliştirme', 2, '#FF4500'),
('Pazarlama', 2, '#32CD32'),
('Tasarım', 2, '#00CED1'),
('Satış', 2, '#DC143C'),
('İnsan Kaynakları', 2, '#4169E1');


-- Alt Grupları ekleme
-- Tech Innovations
-- Yazılım Geliştirme (group_id = 1)
INSERT INTO sub_bands (name, group_id, color)
VALUES
    ('Frontend', 1, '#FFB6C1'),
    ('Backend', 1, '#20B2AA'),
    ('Fullstack', 1, '#FFA07A'),
    ('Android', 1, '#8A2BE2'),
    ('iOS', 1, '#5F9EA0'),
    ('DevOps', 1, '#7FFF00');

-- Pazarlama (group_id = 2)
INSERT INTO sub_bands (name, group_id, color)
VALUES
    ('Dijital Pazarlama', 2, '#FF4500'),
    ('SEO', 2, '#32CD32'),
    ('İçerik Pazarlama', 2, '#4682B4'),
    ('Sosyal Medya', 2, '#FF1493');

-- Destek (group_id = 3)
INSERT INTO sub_bands (name, group_id, color)
VALUES
    ('Müşteri Hizmetleri', 3, '#B22222'),
    ('Teknik Destek', 3, '#4B0082');

-- Tasarım (group_id = 4)
INSERT INTO sub_bands (name, group_id, color)
VALUES
    ('UI/UX Tasarım', 4, '#FF69B4'),
    ('Grafik Tasarım', 4, '#8B4513');

-- Satış (group_id = 5)
INSERT INTO sub_bands (name, group_id, color)
VALUES
    ('Satış Temsilcileri', 5, '#FFD700'),
    ('İş Geliştirme', 5, '#D2691E');

-- İnsan Kaynakları (group_id = 6)
INSERT INTO sub_bands (name, group_id, color)
VALUES
    ('İşe Alım', 6, '#2E8B57'),
    ('Eğitim & Gelişim', 6, '#800000'),
    ('Çalışan Deneyimi', 6, '#000080');

-- Creative Solutions
-- Yazılım Geliştirme (group_id = 7)
INSERT INTO sub_bands (name, group_id, color)
VALUES
    ('Frontend', 7, '#E9967A'),
    ('Backend', 7, '#8FBC8F'),
    ('DevOps', 7, '#708090');

-- Pazarlama (group_id = 8)
INSERT INTO sub_bands (name, group_id, color)
VALUES
    ('Dijital Pazarlama', 8, '#A52A2A'),
    ('SEO', 8, '#556B2F');

-- Tasarım (group_id = 9)
INSERT INTO sub_bands (name, group_id, color)
VALUES
    ('UI/UX Tasarım', 9, '#9932CC'),
    ('Grafik Tasarım', 9, '#B8860B');

-- Satış (group_id = 10)
INSERT INTO sub_bands (name, group_id, color)
VALUES
    ('Satış Temsilcileri', 10, '#C71585'),
    ('İş Geliştirme', 10, '#FF6347');

-- İnsan Kaynakları (group_id = 11)
INSERT INTO sub_bands (name, group_id, color)
VALUES
    ('İşe Alım', 11, '#8B0000'),
    ('Eğitim & Gelişim', 11, '#191970');
