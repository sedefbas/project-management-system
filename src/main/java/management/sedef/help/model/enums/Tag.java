package management.sedef.help.model.enums;

public enum Tag {
    BACKEND("Backend"),
    FRONTEND("Frontend"),
    DATABASE("Database"),
    DEVOPS("DevOps"),
    MOBILE("Mobile"),
    AI("AI"),
    SECURITY("Security"),
    OTHER("Other");

    private final String label;

    Tag(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
