package management.sedef.user.port;

import management.sedef.user.model.UserVerification;
import management.sedef.user.model.entity.UserVerificationEntity;
import management.sedef.user.model.enums.UserVerificationType;


import java.util.Optional;

public interface UserVerificationReadPort {
    Optional<UserVerification> findByTypeAndId(UserVerificationType type, Long id);

}
