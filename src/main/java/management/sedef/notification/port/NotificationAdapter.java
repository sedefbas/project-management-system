package management.sedef.notification.port;

import lombok.RequiredArgsConstructor;
import management.sedef.notification.model.Notification;
import management.sedef.notification.model.document.NotificationDocument;
import management.sedef.notification.model.enums.NotificationStatus;
import management.sedef.notification.model.mapper.NotificaitonToDocumentMapper;
import management.sedef.notification.model.mapper.NotificationDocumentToDomainMapper;
import management.sedef.notification.repository.NotificationRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NotificationAdapter implements NotificationDeletePort,NotificationSavePort,NotificationReadPort {

    private final NotificationRepository notificationRepository;
    private final NotificaitonToDocumentMapper notificaitonToDocumentMapper = NotificaitonToDocumentMapper.initialize();
    private final NotificationDocumentToDomainMapper notificationDocumentToDomainMapper = NotificationDocumentToDomainMapper.initialize();
    private final MongoTemplate mongoTemplate;


    @Override
    public List<Notification> getNotificationsByRecipientId(Long recipientId) {
        List<NotificationDocument> documents = notificationRepository.findByRecipientIdContainingOrderByCreatedAtDesc(recipientId);
        return documents.stream()
                .map(notificationDocumentToDomainMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void updateNotificationStatus(Long recipientId, String notificationId, NotificationStatus status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(notificationId)
                .and("recipientIds." + recipientId).exists(true));

        Update update = new Update();
        update.set("recipientIds." + recipientId, status);

        mongoTemplate.updateFirst(query, update, NotificationDocument.class);
    }

    @Override
    public List<Notification> getNotificationsByRecipientIdAndStatus(Long recipientId, NotificationStatus status) {

        Query query = new Query();
        query.addCriteria(Criteria.where("recipientIds." + recipientId).is(status));

        List<NotificationDocument> documents = mongoTemplate.find(query, NotificationDocument.class);

        return documents.stream()
                .map(notificationDocumentToDomainMapper::map)
                .collect(Collectors.toList());
    }



    @Override
    public Notification saveNotification(Notification notification) {
        NotificationDocument document = notificaitonToDocumentMapper.map(notification);
        NotificationDocument savedDocument = notificationRepository.save(document);
        return notificationDocumentToDomainMapper.map(savedDocument);
    }
}
