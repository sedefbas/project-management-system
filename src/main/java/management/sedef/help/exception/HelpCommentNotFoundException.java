package management.sedef.help.exception;

import management.sedef.common.exception.AbstractNotFoundException;

public class HelpCommentNotFoundException extends AbstractNotFoundException {
    public HelpCommentNotFoundException(String message) {
        super(message);
    }
}
