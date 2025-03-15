package management.sedef.issue.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import management.sedef.issue.kafka.event.*;
import management.sedef.issue.kafka.producer.IssueHistoryProducer;
import management.sedef.issue.kafka.producer.IssueMailProducer;
import management.sedef.issue.model.Issue;
import management.sedef.issue.port.issuePort.IssueReadPort;
import management.sedef.notification.model.enums.NotificationStatus;
import management.sedef.notification.model.enums.NotificationType;
import management.sedef.notification.model.request.NotificationRequest;
import management.sedef.notification.service.NotificationService;
import management.sedef.user.model.User;
import management.sedef.user.port.UserReadPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.Optional;

@Service
@Slf4j
public class IssueUserConsumer {

    private final NotificationService notificationService;
    private final UserReadPort readPort;
    private final IssueReadPort issueReadPort;
    private final İssueMailConsumer i̇ssueMailConsumer;
    private final IssueHistoryProducer issueHistoryProducer;
    private final IssueMailProducer issueMailProducer;

    public IssueUserConsumer(NotificationService notificationService, UserReadPort readPort, IssueReadPort issueReadPort, İssueMailConsumer i̇ssueMailConsumer, IssueHistoryProducer issueHistoryProducer, IssueMailProducer issueMailProducer) {
        this.notificationService = notificationService;
        this.readPort = readPort;
        this.issueReadPort = issueReadPort;
        this.i̇ssueMailConsumer = i̇ssueMailConsumer;
        this.issueHistoryProducer = issueHistoryProducer;
        this.issueMailProducer = issueMailProducer;
    }

    @KafkaListener(
            topics = "${spring.kafka.template.issue-assignment-event-topic}",
            containerFactory = "issueUserKafkaListenerContainerFactory"
    )
    public void consume(IssueUserEvent event) {

        Issue issue = issueReadPort.findById(event.getIssueId());
        Optional<User> assignedUser = readPort.findById(event.getAssignedUserId());
        Optional<User> assignedByUser = readPort.findById(event.getAssignedById());

        if (assignedUser.isPresent() && assignedByUser.isPresent()) {

            String message = "Hello " + assignedUser.get().getFirstName() + ", you have been assigned to the issue: " + issue.getName() +
                    " by " + assignedByUser.get().getFirstName();

            NotificationRequest notification = NotificationRequest.builder()
                    .message(message)
                    .type(NotificationType.IN_APP)
                    .eventType(event.getType())
                    .senderId(event.getAssignedById())
                    .recipientId(event.getAssignedUserId())
                    .projectId(issue.getProject().getId())
                    .isGeneral(false)
                    .notificationStatus(NotificationStatus.READ)
                    .build();

            notificationService.saveNotification(notification);


            IssueHistoryEvent issueHistoryEvent = IssueHistoryEvent.builder()
                    .issueId(event.getIssueId())
                    .eventType(event.getType()) // Update türü belirleniyor
                    .oldValues("")
                    .newValues("")
                    .userId(event.getAssignedById())
                    .AssignedUserId(event.getAssignedUserId())
                    .modifiedAt(Instant.now())
                    .build();

            issueHistoryProducer.sendIssueHistoryEvent(issueHistoryEvent);

        } else {
            // Eğer kullanıcılar veya issue bulunamazsa loglama yapabiliriz
            log.warn("User or Issue not found for event: {}", event);
        }
    }
}
