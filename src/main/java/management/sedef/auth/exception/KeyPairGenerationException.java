package management.sedef.auth.exception;

import management.sedef.common.exception.AbstractServerException;

import java.io.Serial;

public final class KeyPairGenerationException extends AbstractServerException {

    @Serial
    private static final long serialVersionUID = 6533777004102054133L;

    public KeyPairGenerationException(Throwable cause) {
        super("error occurred while generating key pair", cause);
    }

}
