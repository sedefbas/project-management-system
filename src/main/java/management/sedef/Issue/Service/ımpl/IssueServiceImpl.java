package management.sedef.issue.Service.ımpl;

import lombok.RequiredArgsConstructor;
import management.sedef.issue.kafka.event.ActionType;
import management.sedef.issue.kafka.event.IssueHistoryEvent;
import management.sedef.issue.kafka.producer.IssueHistoryProducer;
import management.sedef.label.model.Label;
import management.sedef.label.service.LabelService;
import management.sedef.company.model.Company;
import management.sedef.company.service.CompanyService;
import management.sedef.issue.Service.IssueService;
import management.sedef.issue.model.Issue;
import management.sedef.issue.model.mapper.issue.IssueRequestToDomainMapper;
import management.sedef.issue.model.request.IssueRequest;
import management.sedef.issue.port.issuePort.IssueDeletePort;
import management.sedef.issue.port.issuePort.IssueReadPort;
import management.sedef.issue.port.issuePort.IssueSavePort;
import management.sedef.issue.validation.IssueValidator;
import management.sedef.priority.model.Priority;
import management.sedef.priority.service.PriorityService;
import management.sedef.stage.model.Stage;
import management.sedef.stage.model.enums.StageType;
import management.sedef.stage.service.StageService;
import management.sedef.user.model.User;
import management.sedef.user.service.UserService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueSavePort savePort;
    private final IssueDeletePort deletePort;
    private final IssueReadPort readPort;
    private final IssueRequestToDomainMapper issueRequestToDomainMapper;
    private final CompanyService companyService;
    private final UserService userService;
    private final StageService stageService;
    private final LabelService labelService;
    private final PriorityService priorityService;
    private final IssueValidator validator;
    private final IssueHistoryProducer issueHistoryProducer;



    @Override
    public List<Issue> findIssuesByProject(Long projectId) {
        return readPort.findIssuesByProject(projectId);
    }

    @Override
    public List<Issue> findByStageIdAndProjectId(Long stageId, Long projectId) {
        return readPort.findByStageIdAndProjectId(stageId,projectId);
    }

    @Override
    public Issue findById(Long issueId) {
        return readPort.findById(issueId);
    }


    @Override
    public void create(IssueRequest request, Long companyId, Long projectId, String token) {

        Long count = readPort.countByProjectId(projectId);
        Company company = companyService.findCompanyById(companyId);
        User user = userService.getUserFromToken(token);

        validator.validateMaxIssues(company.getSubscriptionPlan(), count);

        Issue issue = issueRequestToDomainMapper.map(request);
        issue.setCreatedBy(user);

        if (request.getStageId() == null) {
            Stage stage = stageService.findByName(StageType.TODO); // Varsayılan olarak
            issue.setStage(stage);
        }

        savePort.save(issue);
    }


    @Override
    public void delete(Long issueId, String token) {
        Issue issue = readPort.findById(issueId);
        User user = userService.getUserFromToken(token);

        createAndSendHistoryEvent(issueId, "DELETE", "", "", token);

        deletePort.delete(issue);
    }


    @Override
    public void updateStage(Long issueId, StageType type, String token) {
        Stage stage = stageService.findByName(type);
        Issue issue = readPort.findById(issueId);

        String oldStage = issue.getStage().getName().name(); // Eski stage
        String newStage = type.name(); // Yeni stage

        issue.setStage(stage);
        savePort.save(issue);

        createAndSendHistoryEvent(issueId, "UPDATE", "{\"stage\": \"" + oldStage + "\"}", "{\"stage\": \"" + newStage + "\"}", token);
    }

    @Override
    public void updateLabel(Long issueId, Long labelId, String token) {
        Issue issue = readPort.findById(issueId);
        Label label = labelService.findById(labelId);

        String oldLabel = issue.getLabel() != null ? issue.getLabel().getName() : null;
        String newLabel = label.getName();

        issue.setLabel(label);
        savePort.save(issue);

        createAndSendHistoryEvent(issueId, "UPDATE", "{\"label\": \"" + oldLabel + "\"}", "{\"label\": \"" + newLabel + "\"}", token);
    }

    @Override
    public void updatePriority(Long issueId, Long priorityId, String token) {
        Issue issue = readPort.findById(issueId);
        Priority priority = priorityService.findById(priorityId);

        String oldPriority = issue.getPriority() != null ? issue.getPriority().getName() : null;
        String newPriority = priority.getName();

        issue.setPriority(priority);
        savePort.save(issue);

        createAndSendHistoryEvent(issueId, "UPDATE", "{\"priority\": \"" + oldPriority + "\"}", "{\"priority\": \"" + newPriority + "\"}", token);
    }

    private void createAndSendHistoryEvent(Long issueId, String actionType, String oldValues, String newValues, String token) {
        Issue issue = readPort.findById(issueId);
        User user = userService.getUserFromToken(token);

        IssueHistoryEvent event = IssueHistoryEvent.builder()
                .issueId(issueId)
                .actionType(ActionType.valueOf(actionType)) // Update türü belirleniyor
                .oldValues(oldValues)
                .newValues(newValues)
                .userId(user.getId())
                .modifiedAt(Instant.now())
                .build();

        issueHistoryProducer.sendMessage(event);
    }
    
}
