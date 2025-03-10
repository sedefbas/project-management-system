package management.sedef.notification.repository;

import jakarta.transaction.Transactional;
import management.sedef.notification.model.document.NotificationDocument;
import management.sedef.notification.model.enums.NotificationStatus;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends MongoRepository<NotificationDocument,String> {

    List<NotificationDocument> findByRecipientIdsContainingOrderByCreatedAtDesc(Long recipientId);

//    // Bildirimi ID'ye göre bul
//    Optional<NotificationDocument> findByIdAndRecipientIdsContaining(String notificationId, Long recipientId);
//
//    // Kullanıcıya ait okundu/okunmadı durumu için filtreleme
//    List<NotificationDocument> findByRecipientIdsContainingAndUserStatusContaining(Long recipientId, NotificationStatus status);


}
