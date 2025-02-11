package management.sedef.project.exception;

import management.sedef.common.exception.AbstractMaxLimitExceededException;


public class MaxUserLimitReachedException extends AbstractMaxLimitExceededException {
    public MaxUserLimitReachedException(String message) {
        super(message);
    }
}