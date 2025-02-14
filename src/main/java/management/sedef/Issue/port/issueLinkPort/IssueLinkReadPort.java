package management.sedef.issue.port.issueLinkPort;

import management.sedef.issue.model.Issue;
import management.sedef.issue.model.IssueLink;
import management.sedef.issue.model.entity.IssueEntity;
import management.sedef.issue.model.entity.IssueLinkEntity;
import management.sedef.issue.model.enums.IssueLinkType;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueLinkReadPort {
    List<IssueLink> getDependencies(Long issueId);

    IssueLink findByIssueIdAndLinkedIssueId(Long issueId, Long linkedIssueId);

    IssueLink findbyId(Long issueId);
}