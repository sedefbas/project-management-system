package management.sedef.issue.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.issue.exception.IssueHistoryNotFoundException;
import management.sedef.issue.model.IssueHistory;
import management.sedef.issue.model.entity.IssueHistoryEntity;
import management.sedef.issue.model.mapper.issueHistoryMapper.IssueHistoryEntityToDomainMapper;
import management.sedef.issue.model.mapper.issueHistoryMapper.IssueHistoryToEntityMapper;
import management.sedef.issue.port.IssueHistoryPort.IssueHistoryDeletePort;
import management.sedef.issue.port.IssueHistoryPort.IssueHistoryReadPort;
import management.sedef.issue.port.IssueHistoryPort.IssueHistorySavePort;
import management.sedef.issue.repository.IssueHistoryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IssueHistoryAdapter implements IssueHistoryDeletePort, IssueHistorySavePort, IssueHistoryReadPort {

    private final IssueHistoryToEntityMapper issueHistoryToEntityMapper = IssueHistoryToEntityMapper.initialize();
    private final IssueHistoryEntityToDomainMapper issueHistoryEntityToDomainMapper = IssueHistoryEntityToDomainMapper.initialize();
    private final IssueHistoryRepository repository;

    @Override
    public void delete(IssueHistory history) {
        IssueHistoryEntity entity = issueHistoryToEntityMapper.map(history);
        repository.deleteById(entity.getId());
    }

    @Override
    public IssueHistory save(IssueHistory issueHistory) {
        IssueHistoryEntity entity = issueHistoryToEntityMapper.map(issueHistory);
        IssueHistoryEntity savedEntity = repository.save(entity);
        return issueHistoryEntityToDomainMapper.map(savedEntity);
    }

    @Override
    public IssueHistory findById(Long id) {

        IssueHistoryEntity entity = repository.findById(id)
                .orElseThrow(() -> new IssueHistoryNotFoundException("IssueHistory not found with ID: " + id));

        return issueHistoryEntityToDomainMapper.map(entity);
    }

}
