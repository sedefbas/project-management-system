package management.sedef.issue.Service.ımpl;

import lombok.RequiredArgsConstructor;
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

    @Override
    public void create(IssueRequest request,Long companyId, Long projectId ) {

      Long count = readPort.countByProjectId(projectId);
      Company company = companyService.findCompanyById(companyId);

      IssueValidator.validateMaxIssues(company.getSubscriptionPlan(),count);
      Issue issue = issueRequestToDomainMapper.map(request);
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

}
