package management.sedef.auth.scheduler;


import lombok.extern.slf4j.Slf4j;
import management.sedef.auth.port.InvalidTokenDeletePort;
import management.sedef.auth.service.impl.TokenExpirationService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Component
@ConditionalOnProperty(name = "project.scheduler.invalid-tokens-deletion.enable", havingValue = "true")
public class InvalidTokenDeletionScheduler {

    private final InvalidTokenDeletePort invalidTokenDeletePort;
    private final TokenExpirationService tokenExpirationService;

    public InvalidTokenDeletionScheduler(InvalidTokenDeletePort invalidTokenDeletePort, TokenExpirationService tokenExpirationService) {
        this.invalidTokenDeletePort = invalidTokenDeletePort;
        this.tokenExpirationService = tokenExpirationService;
        log.info("InvalidTokenDeletionScheduler is enabled.");
    }

    /**
     * Scheduled method to delete invalid tokens based on the configured cron expression.
     * <p>
     * This method runs periodically based on the cron expression configured in
     * `ays.scheduler.invalid-tokens-deletion.cron`. It retrieves the expiration threshold from the application parameters,
     * calculates the threshold date, and deletes all invalid tokens created before that date.
     * </p>
     */
    @Transactional
    @Scheduled(cron = "${ays.scheduler.invalid-tokens-deletion.cron}")
    public void deleteInvalidTokens() {
        try {
            // Uygulama parametrelerinden token geçerlilik süresini al
            LocalDateTime expirationThreshold = tokenExpirationService.getExpirationThreshold();

            // Geçersiz token'ları belirtilen tarihten önce olanları sil
            invalidTokenDeletePort.deleteAllByCreatedAtBefore(expirationThreshold);
            log.info("Invalid tokens before {} have been deleted.", expirationThreshold);
        } catch (Exception e) {
            log.error("An error occurred while deleting invalid tokens: ", e);
        }
    }
}
