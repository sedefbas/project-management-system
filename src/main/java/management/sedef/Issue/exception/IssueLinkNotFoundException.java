package management.sedef.issue.exception;

import management.sedef.common.exception.AbstractNotFoundException;

public class IssueLinkNotFoundException extends AbstractNotFoundException {
    public IssueLinkNotFoundException(String message) {
        super(message);
    }
}
