package management.sedef.issue.kafka.consumer;

import lombok.extern.slf4j.Slf4j;

import management.sedef.issue.Service.IssueHistoryService;
import management.sedef.issue.Service.IssueService;
import management.sedef.issue.kafka.event.EventType;
import management.sedef.issue.kafka.event.IssueHistoryEvent;
import management.sedef.issue.model.mapper.issueHistoryMapper.IssueHistoryMapper;
import management.sedef.issue.model.request.IssueHistoryRequest;
import management.sedef.user.model.User;
import management.sedef.user.port.UserReadPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class IssueHistoryConsumer {

    private final IssueHistoryService issueHistoryService;
    private final IssueHistoryMapper issueHistoryMapper;
    private final IssueService issueService;
    private final UserReadPort userReadPort;


    public IssueHistoryConsumer(IssueHistoryService issueHistoryService,
                                IssueHistoryMapper issueHistoryMapper,
                                IssueService issueService,
                                UserReadPort userReadPort) {
        this.issueHistoryService = issueHistoryService;
        this.issueHistoryMapper = issueHistoryMapper;
        this.issueService = issueService;
        this.userReadPort = userReadPort;
    }

    @KafkaListener(topics = "${spring.kafka.template.issue-history-event-topic}",
            containerFactory = "issueHistoryKafkaListenerContainerFactory")
    public void consumeIssueCommentHistoryEvent(IssueHistoryEvent event) {
        log.info("Received IssueCommentHistoryEvent: {}", event);


        switch (event.getEventType()) {
            case ISSUE_CREATED:
                log.info("Issue comment created: {}", event.getIssueId());
                handleCreate(event);
                break;

            case ISSUE_PRIORITY_UPDATED:
                log.info("Issue comment updated priority: {}", event.getIssueId());
                handlePriorityUpdate(event);
                break;

            case ISSUE_LABEL_UPDATED:
                log.info("Issue comment updated label: {}", event.getIssueId());
                handleLabelUpdate(event);
                break;

            case ISSUE_STAGE_UPDATED:
                log.info("Issue comment updated stage: {}", event.getIssueId());
                handleStageUpdate(event);
                break;

            case ISSUE_ASSIGNED_TO_USER:
                log.info("Issue assigned to user: {}", event.getIssueId());
                handleAssignmentCreate(event);
                break;

            case ISSUE_UNASSIGNED_FROM_USER:
                log.info("Issue unassigned from user: {}", event.getIssueId());
                handleAssignmentDelete(event);
                break;

            case ISSUE_ROLE_UPDATED:
                log.info("Issue role updated: {}", event.getIssueId());
                handleAssignmentUpdate(event);
                break;

            case ISSUE_COMMENT_CREATED:
                log.info("Issue comment created: {}", event.getIssueId());
                handleIssueCommentCreate(event);
                break;

            case ISSUE_COMMENT_UPDATED:
                log.info("Issue comment updated: {}", event.getIssueId());
                handleIssueCommentUpdate(event);
                break;

            case ISSUE_COMMENT_DELETED:
                log.info("Issue comment deleted: {}", event.getIssueId());
                handleIssueCommentDelete(event);
                break;

            default:
                log.warn("Unknown action type: {}");
                break;
        }
    }

    private void handleCreate(IssueHistoryEvent event) {
        log.info("Handling create for Issue ID: {}", event.getIssueId());
        IssueHistoryRequest request = issueHistoryMapper.mapToRequest(event);

        Optional<User> user = userReadPort.findById(event.getUserId());
        String userName = user.map(u -> u.getFirstName() + " " + u.getLastName()).orElse("Unknown User");

        String changeDescription = String.format("İssue created: '%s' tarafından ",
                userName);
        request.setChangeDescription(changeDescription);
        issueHistoryService.save(request);
    }


    private void handlePriorityUpdate(IssueHistoryEvent event) {
        log.info("Handling priority update for Issue ID: {}", event.getIssueId());
        IssueHistoryRequest request = issueHistoryMapper.mapToRequest(event);

        Optional<User> user = userReadPort.findById(event.getUserId());
        String userName = user.map(u -> u.getFirstName() + " " + u.getLastName()).orElse("Unknown User");

        String changeDescription = String.format("Priority Updated: '%s' tarafından '%s' -> '%s' şeklinde güncellendi",
                userName, event.getOldValues(), event.getNewValues());

        request.setChangeDescription(changeDescription);
        issueHistoryService.save(request);
        log.info("{} for Issue ID: {}", changeDescription, event.getIssueId());
    }

    private void handleLabelUpdate(IssueHistoryEvent event) {
        log.info("Handling label update for Issue ID: {}", event.getIssueId());
        IssueHistoryRequest request = issueHistoryMapper.mapToRequest(event);

        Optional<User> user = userReadPort.findById(event.getUserId());
        String userName = user.map(u -> u.getFirstName() + " " + u.getLastName()).orElse("Unknown User");

        String changeDescription = String.format("Label Updated: '%s' tarafından '%s' -> '%s' şeklinde güncellendi",
                userName, event.getOldValues(), event.getNewValues());

        request.setChangeDescription(changeDescription);
        issueHistoryService.save(request);
        log.info("{} for Issue ID: {}", changeDescription, event.getIssueId());
    }

    private void handleStageUpdate(IssueHistoryEvent event) {
        log.info("Handling stage update for Issue ID: {}", event.getIssueId());
        IssueHistoryRequest request = issueHistoryMapper.mapToRequest(event);

        Optional<User> user = userReadPort.findById(event.getUserId());
        String userName = user.map(u -> u.getFirstName() + " " + u.getLastName()).orElse("Unknown User");

        String changeDescription = String.format("Stage Updated: '%s' tarafından '%s' -> '%s' şeklinde güncellendi",
                userName, event.getOldValues(), event.getNewValues());

        request.setChangeDescription(changeDescription);
        issueHistoryService.save(request);
        log.info("{} for Issue ID: {}", changeDescription, event.getIssueId());
    }


    private void handleAssignmentCreate(IssueHistoryEvent event) {
        log.info("Handling assignment create for Issue ID: {}", event.getIssueId());

        IssueHistoryRequest request = issueHistoryMapper.mapToRequest(event);

        Optional<User> assignedUser = userReadPort.findById(event.getAssignedUserId());
        String assignedUserName = assignedUser.map(u -> u.getFirstName() + " " + u.getLastName()).orElse("Unknown User");

        Optional<User> modifiedByUser = userReadPort.findById(event.getUserId());
        String modifiedByName = modifiedByUser.map(u -> u.getFirstName() + " " + u.getLastName()).orElse("Unknown User");

        String changeDescription = String.format("Issue assigned to '%s' by '%s'", assignedUserName, modifiedByName);
        request.setChangeDescription(changeDescription);
        issueHistoryService.save(request);
    }

    private void handleAssignmentUpdate(IssueHistoryEvent event) {
        log.info("Handling assignment update for Issue ID: {}", event.getIssueId());

        IssueHistoryRequest request = issueHistoryMapper.mapToRequest(event);

        String oldRole = event.getOldValues();
        String newRole = event.getNewValues();


        Optional<User> modifiedByUser = userReadPort.findById(event.getUserId());
        String modifiedByName = modifiedByUser.map(u -> u.getFirstName() + " " + u.getLastName()).orElse("Unknown User");


        Optional<User> assignedUser = userReadPort.findById(event.getAssignedUserId());
        String assignedUserName = assignedUser.map(u -> u.getFirstName() + " " + u.getLastName()).orElse("Unknown User");


        String changeDescription = String.format("Role updated: '%s' by '%s' changed to '%s'.",
                assignedUserName, modifiedByName, newRole);

        request.setChangeDescription(changeDescription);
        issueHistoryService.save(request);
    }




    private void handleAssignmentDelete(IssueHistoryEvent event) {
        log.info("Handling assignment delete for Issue ID: {}", event.getIssueId());

        IssueHistoryRequest request = issueHistoryMapper.mapToRequest(event);

        Optional<User> unassignedUser = userReadPort.findById(event.getUserId());
        String unassignedUserName = unassignedUser.map(u -> u.getFirstName() + " " + u.getLastName()).orElse("Unknown User");

        Optional<User> modifiedByUser = userReadPort.findById(event.getAssignedUserId());
        String modifiedByName = modifiedByUser.map(u -> u.getFirstName() + " " + u.getLastName()).orElse("Unknown User");

        String changeDescription = String.format("Issue unassigned from '%s' by '%s'", unassignedUserName, modifiedByName);
        request.setChangeDescription(changeDescription);
        issueHistoryService.save(request);
    }


    private void handleIssueCommentCreate(IssueHistoryEvent event) {
        log.info("Handling comment create for Issue ID: {}", event.getIssueId());
        // Issue'ya yeni bir yorum eklenmesi işlemini burada gerçekleştirebilirsiniz
    }

    private void handleIssueCommentUpdate(IssueHistoryEvent event) {
        log.info("Handling comment update for Issue ID: {}", event.getIssueId());
        // Issue'ya yapılan bir yorumun güncellenmesi işlemi burada yapılabilir
    }

    private void handleIssueCommentDelete(IssueHistoryEvent event) {
        log.info("Handling comment delete for Issue ID: {}", event.getIssueId());
        // Issue'dan bir yorumun silinmesi işlemi burada yapılabilir
    }

}