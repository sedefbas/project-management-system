package management.sedef.help.exception;

import management.sedef.common.exception.AbstractNotFoundException;

public class HelpNotFoundException extends AbstractNotFoundException {
    public HelpNotFoundException(String message) {
        super(message);
    }
}
