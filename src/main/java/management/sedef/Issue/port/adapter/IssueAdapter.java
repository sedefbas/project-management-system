package management.sedef.issue.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.issue.exception.IssueNotFoundExceptions;
import management.sedef.issue.model.Issue;
import management.sedef.issue.model.entity.IssueEntity;
import management.sedef.issue.model.mapper.issue.IssueEntityToDomainMapper;
import management.sedef.issue.model.mapper.issue.IssueToEntityMapper;
import management.sedef.issue.port.issuePort.IssueDeletePort;
import management.sedef.issue.port.issuePort.IssueReadPort;
import management.sedef.issue.port.issuePort.IssueSavePort;
import management.sedef.issue.repository.IssueRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class IssueAdapter implements IssueReadPort, IssueDeletePort, IssueSavePort {

    private final IssueRepository repository;
    private final IssueEntityToDomainMapper issueEntityToDomainMapper = IssueEntityToDomainMapper.initialize();
    private final IssueToEntityMapper issueToEntityMapper = IssueToEntityMapper.initialize();


    @Override
    public void delete(Issue issue) {
        final IssueEntity issueEntity = issueToEntityMapper.map(issue);
        repository.delete(issueEntity);
    }

    @Override
    public List<Issue> findIssuesByProject(Long projectId) {
        List<IssueEntity> issueEntities = repository.findByProjectId(projectId);

        return issueEntities.stream()
                .map(issueEntityToDomainMapper::map)
                .collect(Collectors.toList());
    }


    @Override
    public List<Issue> findByStageIdAndProjectId(Long stageId, Long projectId) {

        List<IssueEntity> issueEntities = repository.findByStageIdAndProjectId(stageId, projectId);

        return issueEntities.stream()
                .map(issueEntityToDomainMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Issue findById(Long issueId) {
        IssueEntity issueEntity = repository.findById(issueId)
                .orElseThrow(() -> new IssueNotFoundExceptions("Issue not found"));
        return issueEntityToDomainMapper.map(issueEntity);
    }

    @Override
    public Long countByProjectId(Long projectId) {
        return repository.countByProjectId(projectId);
    }

    @Override
    public Issue save(Issue issue) {
        IssueEntity issueEntity = issueToEntityMapper.map(issue);
        repository.save(issueEntity);
        return issueEntityToDomainMapper.map(issueEntity);
    }

}
