package management.sedef.notification.port;

import management.sedef.notification.model.Notification;
import management.sedef.notification.model.enums.NotificationStatus;

import java.util.List;

public interface NotificationReadPort {
    List<Notification> getNotificationsByRecipientId(Long recipientId);
    void updateNotificationStatus(Long recipientId, String notificationId, NotificationStatus status);
    List<Notification> getNotificationsByRecipientIdAndStatus(Long recipientId, NotificationStatus status);


}
