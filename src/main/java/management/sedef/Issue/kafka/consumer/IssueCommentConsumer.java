package management.sedef.issue.kafka.consumer;


import lombok.extern.slf4j.Slf4j;

import management.sedef.issue.kafka.event.IssueCommentEvent;

import management.sedef.issue.kafka.producer.IssueHistoryProducer;
import management.sedef.notification.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class IssueCommentConsumer {

    private final NotificationService notificationService;
    private final IssueHistoryProducer issueHistoryProducer;

    public IssueCommentConsumer(NotificationService notificationService, IssueHistoryProducer issueHistoryProducer) {
        this.notificationService = notificationService;
        this.issueHistoryProducer = issueHistoryProducer;
    }


    @KafkaListener(
           topics = "${spring.kafka.template.issue-comment-event-topic}",
           containerFactory = "issueCommentKafkaListenerContainerFactory"
    )
   public void consume(IssueCommentEvent event) {

//       NotificationRequest notification = NotificationRequest.builder()
//               .message(message)
//               .type(NotificationType.IN_APP)
//               .eventType(event.getEventType())
//               .senderId(event.getAssignedById())
//               .recipientId(event.getAssignedUserId())
//               .projectId(issue.getProject().getId())
//               .isGeneral(false)
//               .notificationStatus(NotificationStatus.READ)
//               .build();
//
//       notificationService.saveNotification(notification);
//
//       IssueHistoryEvent issueHistoryEvent = IssueHistoryEvent.builder()
//               .issueId(event.getIssueId())
//               .eventType(event.getEventType))
//               .oldValues("")
//               .newValues("")
//               .userId(event.getAssignedById())
//               .AssignedUserId(event.getAssignedUserId())
//               .modifiedAt(Instant.now())
//               .build();
//
//       issueHistoryProducer.sendIssueHistoryEvent(issueHistoryEvent// //       ) }
} }
