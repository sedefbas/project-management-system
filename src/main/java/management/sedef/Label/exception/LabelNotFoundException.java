package management.sedef.Label.exception;

import management.sedef.common.exception.AbstractNotFoundException;

public class LabelNotFoundException extends AbstractNotFoundException {
    public LabelNotFoundException(String message) {
        super(message);
    }
}
