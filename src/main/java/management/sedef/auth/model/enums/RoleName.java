package management.sedef.auth.model.enums;

public enum RoleName {
    USER,           // Sistemi temel düzeyde kullanabilen kullanıcı
    PROJECT_MANAGER, // Projeleri yönetme yetkisine sahip kişi
    DEVELOPER,       // Geliştirme yapan kişi (kod yazan)
    TESTER,          // Test süreçlerini yürüten kişi
    SCRUM_MASTER,    // Scrum süreçlerini yöneten kişi
    ADMIN,           // Sistem yöneticisi, tüm yetkilere sahip
    VIEWER,
    COMPANY_OWNER,
    MEMBER,// Yalnızca okuma yetkisi olan kullanıcı
}
