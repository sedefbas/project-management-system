package management.sedef.notification.port;

import management.sedef.notification.model.Notification;

public interface NotificationSavePort {
    Notification saveNotification(Notification notification);

}
