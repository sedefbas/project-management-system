package management.sedef.company.exception;

import management.sedef.common.exception.AbstractConflictException;

public class UserAlreadyAssignedToCompanyException extends AbstractConflictException {
    public UserAlreadyAssignedToCompanyException(String message) {
        super(message);
    }
}
