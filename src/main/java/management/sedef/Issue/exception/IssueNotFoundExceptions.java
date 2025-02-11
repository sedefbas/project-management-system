package management.sedef.issue.exception;

import management.sedef.common.exception.AbstractNotFoundException;



public class IssueNotFoundExceptions extends AbstractNotFoundException {
    public IssueNotFoundExceptions(String message) {
        super(message);
    }
}
