package management.sedef.auth.exception;

import java.io.Serial;

public class KeyPairConversionException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 304959574320527271L;

    public KeyPairConversionException(Throwable cause) {
        super("error occurred while converting key pair", cause);
    }

}
