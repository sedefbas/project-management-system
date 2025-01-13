package management.sedef.auth.exception;

import management.sedef.common.exception.AbstractNotFoundException;

import java.io.Serial;

public final class UserNotFoundByIdException extends AbstractNotFoundException {

    @Serial
    private static final long serialVersionUID = -4173490660693121294L;

    public UserNotFoundByIdException(Long id) {
        super("user does not found! id: " + id);
    }

}
