package management.sedef.issue.model.enums;

public enum IssueLinkType {
    BLOCKS,       // Bu issue başka bir issue'yu blokluyor
    DEPENDS_ON,   // Bu issue başka bir issue'ya bağımlı
    RELATES_TO    // Bu issue sadece ilişkili (zorunlu değil)
}