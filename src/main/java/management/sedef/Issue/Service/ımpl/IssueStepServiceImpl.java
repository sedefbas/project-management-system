package management.sedef.issue.Service.ımpl;


import lombok.RequiredArgsConstructor;
import management.sedef.issue.Service.IssueStepService;
import management.sedef.issue.model.IssueStep;
import management.sedef.issue.model.mapper.issueStep.IssueStepRequestToDomainMapper;
import management.sedef.issue.model.request.IssueStepRequest;
import management.sedef.issue.model.request.IssueStepUpdateRequest;
import management.sedef.issue.port.issuePort.IssueReadPort;
import management.sedef.issue.port.issueStepPort.IssueStepDeletePort;
import management.sedef.issue.port.issueStepPort.IssueStepReadPort;
import management.sedef.issue.port.issueStepPort.IssueStepSavePort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueStepServiceImpl implements IssueStepService {

    private final IssueStepReadPort readPort;
    private final IssueStepSavePort savePort;
    private final IssueStepDeletePort deletePort;
    private final IssueReadPort issueReadPort;
    private final IssueStepRequestToDomainMapper issueRequestToDomainMapper = IssueStepRequestToDomainMapper.initialize();

    @Override
    public void create(IssueStepRequest request, Long issueId) {
        Integer maxStepNumber = readPort.findMaxStepNumberByIssueId(issueId);

        int nextStepNumber = maxStepNumber + 1;
        IssueStep issueStep = issueRequestToDomainMapper.map(request);

        issueStep.setStepNumber(nextStepNumber);
        issueStep.setCreatedAt(Instant.now());
        issueStep.setIssue(issueReadPort.findById(issueId));
        savePort.save(issueStep);
    }


    @Override
    public void delete(Long issueStepId) {
        deletePort.delete(readPort.findById(issueStepId));
    }

    @Override
    public List<IssueStep> findByIssueIdOrderByStepNumberAsc(Long issueId) {
        return readPort.findByIssueIdOrderByStepNumberAsc(issueId);
    }

    @Override
    public void update(IssueStepUpdateRequest request, Long issueStepId) {
        IssueStep issueStep = readPort.findById(issueStepId);
        issueStep.setDescription(request.getDescription());
        issueStep.setIsDone(request.getIsDone());
        savePort.save(issueStep);
    }

}

