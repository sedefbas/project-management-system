package management.sedef.help.model.enums;

public enum Language {
    JAVA("Java"),
    PYTHON("Python"),
    JAVASCRIPT("JavaScript"),
    C("C"),
    C_PLUS_PLUS("C++"),
    PHP("PHP"),
    RUBY("Ruby"),
    GO("Go"),
    SWIFT("Swift"),
    OTHER("Other");

    private final String label;

    Language(String label) {
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
