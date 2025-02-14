package management.sedef.stage.exceptions;

import management.sedef.common.exception.AbstractNotFoundException;

public class StageNotFoundException extends AbstractNotFoundException {
    public StageNotFoundException(String message) {
        super(message);
    }
}
