package management.sedef.issue.model.mapper.issueHistoryMapper;

import lombok.RequiredArgsConstructor;
import management.sedef.company.service.CompanyService;
import management.sedef.issue.kafka.event.IssueHistoryEvent;
import management.sedef.issue.model.request.IssueHistoryRequest;
import management.sedef.project.service.ProjectService;
import management.sedef.user.port.UserReadPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueHistoryMapper {

    private final CompanyService companyService;
    private final ProjectService projectService;
    private final UserReadPort userReadPort;

    public IssueHistoryRequest mapToRequest(IssueHistoryEvent event) {

        return IssueHistoryRequest.builder()
                .issueId(event.getIssueId())
                .modifiedById(event.getUserId())
                .build();
    }
}
