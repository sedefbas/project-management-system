package management.sedef.project.exception;

import management.sedef.common.exception.AbstractConflictException;

public class GroupAlreadyExist extends AbstractConflictException {
    public GroupAlreadyExist(String message) {
        super(message);
    }
}
