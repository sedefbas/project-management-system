package management.sedef.notification.model.enums;

public enum EventType {
    ISSUE_CREATED("Issue Created"),       // Yeni bir issue oluşturuldu
    ISSUE_UPDATED("Issue Updated"),       // Mevcut bir issue güncellendi
    ISSUE_ASSIGNED("Issue Assigned"),     // Bir issue bir kullanıcıya atandı
    ISSUE_COMMENTED("Issue Commented"),   // Bir issue'ye yorum yapıldı

    PROJECT_CREATED("Project Created"),   // Yeni bir proje oluşturuldu
    PROJECT_UPDATED("Project Updated"),   // Projede değişiklik yapıldı
    PROJECT_DELETED("Project Deleted"),   // Proje silindi

    USER_INVITED("User Invited"),         // Kullanıcı bir projeye davet edildi
    USER_REMOVED("User Removed"),         // Kullanıcı bir projeden çıkarıldı

    GENERAL_ANNOUNCEMENT("General Announcement"); // Genel bir duyuru yapıldı

    private final String value;

    EventType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
