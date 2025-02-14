package management.sedef.common.exception;
import java.io.Serial;

public abstract class AbstractValidationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1234567890123456789L;

    // Default constructor
    protected AbstractValidationException(String message) {
        super(message);
    }

    // Constructor with cause
    protected AbstractValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
