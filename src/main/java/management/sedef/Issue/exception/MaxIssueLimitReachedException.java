package management.sedef.issue.exception;

import management.sedef.common.exception.AbstractMaxLimitExceededException;

public class MaxIssueLimitReachedException extends AbstractMaxLimitExceededException {
    public MaxIssueLimitReachedException(String message) {
        super(message);
    }
}
