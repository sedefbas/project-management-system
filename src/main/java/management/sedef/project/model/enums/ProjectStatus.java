package management.sedef.project.model.enums;

public enum ProjectStatus {

    NOT_STARTED("Henüz Başlamadı"),
    IN_PROGRESS("Devam Ediyor"),
    ON_HOLD("Beklemede"),
    COMPLETED("Tamamlandı"),
    CANCELED("İptal Edildi"),
    ARCHIVED("Arşivlendi");

    private final String description;

    ProjectStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
