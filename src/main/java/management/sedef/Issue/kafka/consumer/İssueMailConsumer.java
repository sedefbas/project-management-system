package management.sedef.issue.kafka.consumer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import management.sedef.issue.Service.IssueAssignmentService;
import management.sedef.issue.kafka.event.IssueMailEvent;
import management.sedef.issue.model.IssueAssignment;
import management.sedef.user.service.UserEmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class İssueMailConsumer {

    private  IssueAssignmentService issueAssignmentService;
    private  UserEmailService userEmailService;

    public İssueMailConsumer(IssueAssignmentService issueAssignmentService, UserEmailService userEmailService) {
        this.issueAssignmentService = issueAssignmentService;
        this.userEmailService = userEmailService;
    }

    @KafkaListener(topics = "${spring.kafka.template.issue-mail-event-topic}", groupId = "mail-group")
    public void consumeIssueMailEvent(IssueMailEvent event) {
        log.info("Received IssueMailEvent: {}", event);

        IssueAssignment issueAssignment = issueAssignmentService.findById(event.getIssueId());
        if (issueAssignment != null) {
            userEmailService.reportIssue(issueAssignment);
        } else {
            log.warn("IssueAssignment not found for id: {}", event.getIssueId());
        }
    }
}
