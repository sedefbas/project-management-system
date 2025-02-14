package management.sedef.issue.exception;

import management.sedef.common.exception.AbstractValidationException;

public class IssueDependencyException extends AbstractValidationException {
    public IssueDependencyException(String message) {
        super(message);
    }

    protected IssueDependencyException(String message, Throwable cause) {
        super(message, cause);
    }
}
