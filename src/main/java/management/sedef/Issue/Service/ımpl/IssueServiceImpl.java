package management.sedef.issue.Service.ımpl;

import lombok.RequiredArgsConstructor;
import management.sedef.Label.model.Label;
import management.sedef.Label.service.LabelService;
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
    public void delete(Long issueId) {
       Issue issue =  readPort.findById(issueId);
        deletePort.delete(issue);
    }

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
    public void updateStage(Long issueId, StageType type) {
        Stage stage = stageService.findByName(type);
        Issue issue = readPort.findById(issueId);

        validator.validateIssueStageChange(issueId,type);

        issue.setStage(stage);
        savePort.save(issue);
    }

    @Override
    public void updateLabel(Long issueId, Long labelId ) {
        Issue issue = readPort.findById(issueId);
        Label label = labelService.findById(labelId);
        issue.setLabel(label);
        savePort.save(issue);
    }

    @Override
    public void updatePriority(Long issueId, Long priorityId ) {
        Issue issue = readPort.findById(issueId);
        Priority priority = priorityService.findById(priorityId);
        issue.setPriority(priority);
        savePort.save(issue);
    }

}
