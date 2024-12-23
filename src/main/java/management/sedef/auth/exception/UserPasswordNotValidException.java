package management.sedef.auth.exception;

import management.sedef.common.exception.AbstractAuthException;

import java.io.Serial;

public final class UserPasswordNotValidException extends AbstractAuthException {

    @Serial
    private static final long serialVersionUID = 359664997679732461L;

    public UserPasswordNotValidException() {
        super("user password is not valid");
    }

}
