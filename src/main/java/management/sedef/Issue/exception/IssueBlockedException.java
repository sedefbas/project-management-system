package management.sedef.issue.exception;

import management.sedef.common.exception.AbstractValidationException;

public class IssueBlockedException extends AbstractValidationException {
    public IssueBlockedException(String message) {
        super(message);
    }
}
