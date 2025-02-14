package management.sedef.stage.model.enums;

public enum StageType {

    TODO("To Do", "Görev oluşturuldu, beklemede", true),
    IN_PROGRESS("In Progress", "Üzerinde çalışılıyor", false),
    IN_REVIEW("In Review", "Kod gözden geçiriliyor", false),
    DONE("Done", "Tamamlandı", false),
    BLOCKED("Blocked", "Engellendi, devam edilemiyor", false),
    BACKLOG("Backlog", "Gelecekte yapılacaklar", false),
    TESTING("Testing", "Test aşamasında", false),
    DEPLOYED("Deployed", "Canlıya alındı", false);

    private final String name;
    private final String context;
    private final boolean isDefault;

    StageType(String name, String context, boolean isDefault) {
        this.name = name;
        this.context = context;
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }

    public String getContext() {
        return context;
    }

    public boolean isDefault() {
        return isDefault;
    }
}
