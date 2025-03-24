package management.sedef.project.exception;

import management.sedef.common.exception.AbstractMaxLimitExceededException;

public class MaxProjectLimitReachedException extends AbstractMaxLimitExceededException {
    public MaxProjectLimitReachedException(String message) {
        super(message);
    }
}
