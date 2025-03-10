package management.sedef.notification.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.notification.model.Notification;
import management.sedef.notification.model.document.NotificationDocument;
import management.sedef.project.model.mapper.project.ProjectEntityToDomainMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificationDocumentToDomainMapper extends BaseMapper<NotificationDocument, Notification> {
    static NotificationDocumentToDomainMapper initialize(){
        return Mappers.getMapper(NotificationDocumentToDomainMapper.class);
    }
}
