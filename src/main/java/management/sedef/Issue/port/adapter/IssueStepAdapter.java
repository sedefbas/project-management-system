package management.sedef.issue.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.issue.exception.IssueStepNotFoundException;
import management.sedef.issue.model.IssueStep;
import management.sedef.issue.model.entity.IssueStepEntity;
import management.sedef.issue.model.mapper.issueStep.IssueStepEntityToDomainMapper;
import management.sedef.issue.model.mapper.issueStep.IssueStepToEntityMapper;
import management.sedef.issue.port.issueStepPort.IssueStepDeletePort;
import management.sedef.issue.port.issueStepPort.IssueStepReadPort;
import management.sedef.issue.port.issueStepPort.IssueStepSavePort;
import management.sedef.issue.repository.IssueStepRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class IssueStepAdapter implements IssueStepSavePort, IssueStepReadPort, IssueStepDeletePort {

    private final IssueStepRepository issueStepRepository;
    private final IssueStepEntityToDomainMapper issueStepEntityToDomainMapper = IssueStepEntityToDomainMapper.initialize();
    private final IssueStepToEntityMapper issueStepToEntityMapper = IssueStepToEntityMapper.initialize();

    @Override
    public IssueStep save(IssueStep issueStep) {
        IssueStepEntity issueStepEntity = issueStepToEntityMapper.map(issueStep);
        IssueStepEntity savedEntity = issueStepRepository.save(issueStepEntity);
        return issueStepEntityToDomainMapper.map(savedEntity);
    }

    @Override
    public void delete(IssueStep issueStep) {
        IssueStepEntity issueStepEntity = issueStepToEntityMapper.map(issueStep);
        issueStepRepository.delete(issueStepEntity);
    }

    @Override
    public IssueStep findById(Long issueStepId) {
        IssueStepEntity issueStepEntity = issueStepRepository.findById(issueStepId)
                .orElseThrow(() -> new IssueStepNotFoundException("IssueStep not found with id: " + issueStepId));
        return issueStepEntityToDomainMapper.map(issueStepEntity);
    }

    @Override
    public List<IssueStep> findByIssueIdOrderByStepNumberAsc(Long issueId) {
        List<IssueStepEntity> issueStepEntities = issueStepRepository.findByIssueIdOrderByStepNumberAsc(issueId);
        return issueStepEntities.stream()
                .map(issueStepEntityToDomainMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Integer findMaxStepNumberByIssueId(Long issueId) {
        return issueStepRepository.findMaxStepNumberByIssueId(issueId);
    }
}
