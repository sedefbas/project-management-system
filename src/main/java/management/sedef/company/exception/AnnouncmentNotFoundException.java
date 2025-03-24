package management.sedef.company.exception;

import management.sedef.common.exception.AbstractNotFoundException;

public class AnnouncmentNotFoundException extends AbstractNotFoundException {
    public AnnouncmentNotFoundException(String message) {
        super(message);
    }
}
