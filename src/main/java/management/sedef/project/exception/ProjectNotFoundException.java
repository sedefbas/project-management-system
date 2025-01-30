package management.sedef.project.exception;

import management.sedef.common.exception.AbstractNotFoundException;

public class ProjectNotFoundException extends AbstractNotFoundException {
    public ProjectNotFoundException(String message) {
        super(message);
    }
}
