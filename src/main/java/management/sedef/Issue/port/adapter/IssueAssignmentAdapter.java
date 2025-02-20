package management.sedef.issue.port.adapter;


import lombok.RequiredArgsConstructor;
import management.sedef.issue.exception.IssueAssignmentNotFoundException;
import management.sedef.issue.model.IssueAssignment;
import management.sedef.issue.model.entity.IssueAssignmentEntity;
import management.sedef.issue.model.mapper.issueAssignment.IssueAssignmentEntityToDomainMapper;
import management.sedef.issue.model.mapper.issueAssignment.IssueAssignmentToEntityMapper;
import management.sedef.issue.port.IssueAssignmentPort.IssueAssignmentDeletePort;
import management.sedef.issue.port.IssueAssignmentPort.IssueAssignmentReadPort;
import management.sedef.issue.port.IssueAssignmentPort.IssueAssignmentSavePort;
import management.sedef.issue.repository.IssueAssignmentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class IssueAssignmentAdapter implements IssueAssignmentSavePort, IssueAssignmentReadPort, IssueAssignmentDeletePort {

    private final IssueAssignmentRepository repository;
    private final IssueAssignmentEntityToDomainMapper assignmentEntityToDomainMapper = IssueAssignmentEntityToDomainMapper.initialize();
    private final IssueAssignmentToEntityMapper assignmentToEntityMapper = IssueAssignmentToEntityMapper.initialize();

    @Override
    public void delete(IssueAssignment issueAssignment) {
        IssueAssignmentEntity entity = assignmentToEntityMapper.map(issueAssignment);
        repository.delete(entity);
    }

    @Override
    public IssueAssignment save(IssueAssignment issueAssignment ) {
        IssueAssignmentEntity entity = assignmentToEntityMapper.map(issueAssignment);
        IssueAssignmentEntity savedEntity = repository.save(entity);
        return assignmentEntityToDomainMapper.map(savedEntity);
    }

    @Override
    public List<IssueAssignment> findByIssueId(Long issueId) {
        List<IssueAssignmentEntity> issueAssignmentEntity = repository.findByIssueId(issueId);
        return issueAssignmentEntity.stream()
                .map(assignmentEntityToDomainMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public IssueAssignment findByIssueIdAndAssignedUserId(Long issueId, Long userId) {
        IssueAssignmentEntity issueAssignmentEntity = repository.findByIssueIdAndAssignedUserId(issueId,userId)
                .orElseThrow(() -> new IssueAssignmentNotFoundException("Issue assignment not found for issueId: " + issueId + " and userId: " + userId));
        return assignmentEntityToDomainMapper.map(issueAssignmentEntity);
    }

}
