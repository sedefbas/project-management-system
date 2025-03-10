package management.sedef.notification.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.notification.model.Notification;
import management.sedef.notification.model.request.NotificationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface NotificationRequestToNotificationMapper  extends BaseMapper<NotificationRequest, Notification> {
    static NotificationRequestToNotificationMapper initialize(){
        return Mappers.getMapper(NotificationRequestToNotificationMapper.class);
    }
}
