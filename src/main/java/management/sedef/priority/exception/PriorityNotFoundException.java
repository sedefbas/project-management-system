package management.sedef.priority.exception;

import management.sedef.common.exception.AbstractNotFoundException;

public class PriorityNotFoundException extends AbstractNotFoundException {
    public PriorityNotFoundException(String message) {
        super(message);
    }
}
