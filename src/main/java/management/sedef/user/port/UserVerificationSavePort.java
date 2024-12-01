package management.sedef.user.port;

import management.sedef.user.model.UserVerification;

public interface UserVerificationSavePort {
    UserVerification save(UserVerification userVerification);
}
