package management.sedef.company.exception;

import management.sedef.common.exception.AbstractNotFoundException;

public class GroupNotFoundException extends AbstractNotFoundException {
    public GroupNotFoundException(String message) {
        super(message);
    }
}
