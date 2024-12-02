package management.sedef.auth.exception;

import java.io.Serial;

public final class UserVerificationIsNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3026197679369190902L;

    public UserVerificationIsNotFoundException(Long id) {
        super("User Verification ID does not found! verificationId: " + id);
    }

}
