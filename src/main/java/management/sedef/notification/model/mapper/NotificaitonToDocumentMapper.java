package management.sedef.notification.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.notification.model.Notification;
import management.sedef.notification.model.document.NotificationDocument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificaitonToDocumentMapper extends BaseMapper<Notification, NotificationDocument> {
    static NotificaitonToDocumentMapper initialize(){
        return Mappers.getMapper(NotificaitonToDocumentMapper.class);
    }
}
