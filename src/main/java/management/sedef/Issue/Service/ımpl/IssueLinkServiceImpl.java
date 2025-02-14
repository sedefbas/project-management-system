package management.sedef.issue.Service.ımpl;

import lombok.RequiredArgsConstructor;
import management.sedef.issue.Service.IssueLinkService;
import management.sedef.issue.exception.IssueLinkNotFoundException;
import management.sedef.issue.model.Issue;
import management.sedef.issue.model.IssueLink;
import management.sedef.issue.model.enums.IssueLinkType;
import management.sedef.issue.model.mapper.issueLink.IssueLinkRequestToDomainMapper;
import management.sedef.issue.model.request.IssueLinkRequest;
import management.sedef.issue.port.issueLinkPort.IssueLinkDeletePort;
import management.sedef.issue.port.issueLinkPort.IssueLinkReadPort;
import management.sedef.issue.port.issueLinkPort.IssueLinkSavePort;
import management.sedef.issue.port.issuePort.IssueReadPort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class IssueLinkServiceImpl implements IssueLinkService {

    private final IssueLinkDeletePort deletePort;
    private final IssueLinkSavePort savePort;
    private final IssueLinkReadPort readPort;
    private final IssueLinkRequestToDomainMapper issueLinkRequestToDomainMapper;



    @Override
    public List<IssueLink> getDependencies(Long issueId) {
       return readPort.getDependencies(issueId);
    }

    @Override
    public Map<IssueLinkType, Set<Issue>> getLinkedIssues(Long issueId) {

        List<IssueLink> issueLinks = readPort.getDependencies(issueId);


        Map<IssueLinkType, Set<Issue>> linkedIssues = new HashMap<>();

        for (IssueLink link : issueLinks) {
            linkedIssues
                    .computeIfAbsent(link.getLinkType(), k -> new HashSet<>())
                    .add(link.getLinkedIssue());
        }

        return linkedIssues;
    }


    @Override
    public void addDependency(IssueLinkRequest request) {
        IssueLink existingLink = readPort.findByIssueIdAndLinkedIssueId(request.getIssueId(), request.getLinkedIssueId());

        if (existingLink != null) {
            throw new IssueLinkNotFoundException("This dependency already exists.");
        }

        IssueLink issueLink = issueLinkRequestToDomainMapper.toDomain(request);
        savePort.save(issueLink);
    }

    @Override
    public void removeDependency(Long issueId, Long dependentIssueId) {
        IssueLink issueLink =  readPort.findByIssueIdAndLinkedIssueId(issueId,dependentIssueId);
        deletePort.removeDependency(issueLink);
    }




}
