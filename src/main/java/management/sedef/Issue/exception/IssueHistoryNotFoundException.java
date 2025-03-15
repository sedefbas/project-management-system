package management.sedef.issue.exception;

import management.sedef.common.exception.AbstractNotFoundException;

public class IssueHistoryNotFoundException extends AbstractNotFoundException {
    public IssueHistoryNotFoundException(String message) {
        super(message);
    }
}
