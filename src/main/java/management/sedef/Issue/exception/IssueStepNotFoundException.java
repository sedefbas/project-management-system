package management.sedef.issue.exception;

import management.sedef.common.exception.AbstractNotFoundException;

public class IssueStepNotFoundException extends AbstractNotFoundException {
    public IssueStepNotFoundException(String message) {
        super(message);
    }
}
