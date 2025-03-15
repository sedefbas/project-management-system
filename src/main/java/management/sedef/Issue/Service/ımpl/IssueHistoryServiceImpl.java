package management.sedef.issue.Service.ımpl;

import lombok.RequiredArgsConstructor;
import management.sedef.issue.Service.IssueHistoryService;
import management.sedef.issue.model.IssueHistory;
import management.sedef.issue.model.mapper.issueHistoryMapper.IssueHistoryRequestToDomainMapper;
import management.sedef.issue.model.request.IssueHistoryRequest;
import management.sedef.issue.port.IssueHistoryPort.IssueHistoryDeletePort;
import management.sedef.issue.port.IssueHistoryPort.IssueHistoryReadPort;
import management.sedef.issue.port.IssueHistoryPort.IssueHistorySavePort;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class IssueHistoryServiceImpl implements IssueHistoryService {

    private final IssueHistorySavePort savePort;
    private final IssueHistoryDeletePort deletePort;
    private final IssueHistoryReadPort readPort;
    private final IssueHistoryRequestToDomainMapper issueHistoryRequestToDomainMapper;



    @Override
    public void delete(Long historyId) {
        IssueHistory history = readPort.findById(historyId);
        deletePort.delete(history);
    }


    @Override
    public IssueHistory save(IssueHistoryRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("IssueHistoryRequest cannot be null");
        }
        IssueHistory issueHistory = issueHistoryRequestToDomainMapper.map(request);
        issueHistory.setExpirationDate(Instant.now());
        return savePort.save(issueHistory);
    }

}
