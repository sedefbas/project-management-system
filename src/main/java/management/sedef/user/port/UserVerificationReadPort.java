package management.sedef.user.port;

import management.sedef.user.model.UserVerification;

import java.util.Optional;

public interface UserVerificationReadPort {
    Optional<UserVerification> findById(Long id);
}
