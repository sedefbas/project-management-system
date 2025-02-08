package management.sedef.user.exception;

import management.sedef.common.exception.AbstractConflictException;

public class UserAlreadyExistsException extends AbstractConflictException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
