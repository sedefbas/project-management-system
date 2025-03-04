package management.sedef.auth.port;

import java.time.LocalDateTime;

public interface InvalidTokenDeletePort {
    void deleteAllByCreatedAtBefore(LocalDateTime expirationThreshold);

}
