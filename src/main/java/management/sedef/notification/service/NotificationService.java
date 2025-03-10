package management.sedef.notification.service;

import management.sedef.notification.model.Notification;
import management.sedef.notification.model.request.NotificationRequest;

public interface NotificationService {
    Notification saveNotification(NotificationRequest request);


}
