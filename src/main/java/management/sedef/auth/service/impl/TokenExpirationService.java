package management.sedef.auth.service.impl;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenExpirationService {

    private final int tokenExpirationDays = 30; // Örnek olarak, 30 günden eski token'ları sil

    public LocalDateTime getExpirationThreshold() {
        return LocalDateTime.now().minusDays(tokenExpirationDays);
    }
}
