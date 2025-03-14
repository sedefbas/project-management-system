package management.sedef.issue.exception;

import management.sedef.common.exception.AbstractNotFoundException;

public class IssueCommentNotFoundException extends AbstractNotFoundException {
    public IssueCommentNotFoundException(String message) {
        super(message);
    }
}
