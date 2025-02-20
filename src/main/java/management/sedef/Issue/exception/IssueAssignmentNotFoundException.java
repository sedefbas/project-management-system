package management.sedef.issue.exception;

import management.sedef.common.exception.AbstractNotFoundException;


public class IssueAssignmentNotFoundException extends AbstractNotFoundException {
    public IssueAssignmentNotFoundException(String message) {
        super(message);
    }
}
