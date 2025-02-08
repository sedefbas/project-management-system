package management.sedef.project.exception;

import management.sedef.common.exception.AbstractNotFoundException;

public class ProjectUserNotFoundException extends AbstractNotFoundException {
    public ProjectUserNotFoundException(String message) {
        super(message);
    }
}
