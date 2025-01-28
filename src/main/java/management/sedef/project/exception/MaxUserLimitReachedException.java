package management.sedef.project.exception;

public class MaxUserLimitReachedException extends RuntimeException {
    public MaxUserLimitReachedException(String message) {
        super(message);
    }
}