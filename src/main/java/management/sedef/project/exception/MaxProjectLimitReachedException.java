package management.sedef.project.exception;

public class MaxProjectLimitReachedException extends RuntimeException {
    public MaxProjectLimitReachedException(String message) {
        super(message);
    }
}
