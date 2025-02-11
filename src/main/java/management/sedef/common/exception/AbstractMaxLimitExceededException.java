package management.sedef.common.exception;

import java.io.Serial;

public abstract class AbstractMaxLimitExceededException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3135425827857563572L;

    // Default constructor
    protected AbstractMaxLimitExceededException(String message) {
        super(message);
    }

    // Constructor with cause
    protected AbstractMaxLimitExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}
