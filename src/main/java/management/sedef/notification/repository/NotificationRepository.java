package management.sedef.notification.repository;

import management.sedef.notification.model.document.NotificationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

public interface NotificationRepository extends MongoRepository<NotificationDocument,String> {

    List<NotificationDocument> findByRecipientIdContainingOrderByCreatedAtDesc(Long recipientId);

//    // Bildirimi ID'ye göre bul
//    Optional<NotificationDocument> findByIdAndRecipientIdsContaining(String notificationId, Long recipientId);
//
//    // Kullanıcıya ait okundu/okunmadı durumu için filtreleme
//    List<NotificationDocument> findByRecipientIdsContainingAndUserStatusContaining(Long recipientId, NotificationStatus status);


}
