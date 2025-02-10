package management.sedef.priority.exception;

import management.sedef.common.exception.AbstractNotFoundException;

public class CompanyPriorityNotFoundException extends AbstractNotFoundException {
    public CompanyPriorityNotFoundException(String message) {
        super(message);
    }
}
