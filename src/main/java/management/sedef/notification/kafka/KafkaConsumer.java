package management.sedef.notification.kafka;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import management.sedef.notification.config.NotificationEvent;
import management.sedef.notification.model.Notification;
import management.sedef.notification.model.enums.EventType;
import management.sedef.notification.model.enums.NotificationStatus;
import management.sedef.notification.model.enums.NotificationType;
import management.sedef.notification.model.mapper.NotificationRequestToNotificationMapper;
import management.sedef.notification.model.request.NotificationRequest;
import management.sedef.notification.port.NotificationAdapter;
import management.sedef.notification.port.NotificationReadPort;
import management.sedef.notification.port.NotificationSavePort;
import management.sedef.notification.service.NotificationService;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class KafkaConsumer {

    private NotificationService service;

    public KafkaConsumer(NotificationService service) {
        this.service = service;
    }

    @KafkaListener(topics = "${spring.kafka.template.notification-created-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(NotificationEvent event) {
        // Notification nesnesini oluştur
        NotificationRequest notification = NotificationRequest.builder()
                .message("User " + event.getUserId() + " has joined the project " + event.getProjeId())
                .type(NotificationType.IN_APP)  // Örnek: Bilgilendirme notifikasyonu
                .eventType(EventType.ISSUE_COMMENTED) // Örnek event tipi
                .senderId(event.getUserId())
                .projectId(event.getProjeId())
                .isGeneral(false) // Kullanıcıya özel bir bildirim
                .recipientIds(Map.of(event.getUserId(), NotificationStatus.READ)) // Kullanıcıya "okunmamış" olarak atanıyor
                .build();


        service.saveNotification(notification);
    }
}
