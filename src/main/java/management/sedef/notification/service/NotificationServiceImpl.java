package management.sedef.notification.service;


import lombok.RequiredArgsConstructor;
import management.sedef.notification.model.Notification;
import management.sedef.notification.model.mapper.NotificationRequestToNotificationMapper;
import management.sedef.notification.model.request.NotificationRequest;
import management.sedef.notification.port.NotificationDeletePort;
import management.sedef.notification.port.NotificationReadPort;
import management.sedef.notification.port.NotificationSavePort;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationReadPort readPort;
    private final NotificationSavePort savePort;
    private final NotificationDeletePort deletePort;


    private final NotificationRequestToNotificationMapper notificationRequestToNotificationMapper = NotificationRequestToNotificationMapper.initialize();


    @Override
    public Notification saveNotification(NotificationRequest request) {
        Notification notification = notificationRequestToNotificationMapper.map(request);
        notification.setCreatedAt(Instant.now());  // Şu anki zamanı set ediyoruz
        return savePort.saveNotification(notification);
    }

}
