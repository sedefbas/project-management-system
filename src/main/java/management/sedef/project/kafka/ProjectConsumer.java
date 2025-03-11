package management.sedef.project.kafka;

import management.sedef.notification.model.enums.EventType;
import management.sedef.notification.model.enums.NotificationStatus;
import management.sedef.notification.model.enums.NotificationType;
import management.sedef.notification.model.request.NotificationRequest;
import management.sedef.notification.service.NotificationService;
import management.sedef.project.model.Project;
import management.sedef.project.port.projectPort.ProjectReadAdapter;
import management.sedef.user.model.User;
import management.sedef.user.port.UserReadPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ProjectConsumer {

    private NotificationService notificationService;
    private ProjectReadAdapter readAdapter;
    private UserReadPort readPort;

    public ProjectConsumer(NotificationService notificationService, ProjectReadAdapter readAdapter, UserReadPort readPort) {
        this.notificationService = notificationService;
        this.readAdapter = readAdapter;
        this.readPort = readPort;
    }

    @KafkaListener(topics = "${spring.kafka.template.project-user-event-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ProjectUserEvent event) {

        Project project = readAdapter.findById(event.getProjeId());

        Optional<User> recipientUser = readPort.findById(event.getRecipientUserId());

        Optional<User> taskAssignerUser = readPort.findById(event.getTaskAssignerId());

        String message = "Hello " + recipientUser.get().getFirstName() + ", you have been assigned to the project: " + project.getName() +
                " by " + taskAssignerUser.get().getFirstName();

        NotificationRequest notification = NotificationRequest.builder()
                .message(message)
                .type(NotificationType.IN_APP)
                .eventType(EventType.PROJECT_ASSIGNED_TO_USER)
                .senderId(event.getTaskAssignerId())
                .recipientId(event.getRecipientUserId())
                .isGeneral(false)
                .notificationStatus(NotificationStatus.READ)
                .projectId(event.getProjeId())
                .issueId(null)
                .build();

        // Notification'ı kaydediyoruz
        notificationService.saveNotification(notification);
    }

}
