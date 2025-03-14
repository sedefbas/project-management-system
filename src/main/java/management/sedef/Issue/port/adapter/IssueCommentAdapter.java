package management.sedef.issue.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.issue.exception.IssueCommentNotFoundException;
import management.sedef.issue.model.IssueComment;
import management.sedef.issue.model.entity.IssueCommentEntity;
import management.sedef.issue.model.mapper.issueComment.IssueCommentEntityToDomainMapper;
import management.sedef.issue.model.mapper.issueComment.IssueCommentToEntityMapper;
import management.sedef.issue.port.IssueCommentPort.IssueCommentDeletePort;
import management.sedef.issue.port.IssueCommentPort.IssueCommentReadPort;
import management.sedef.issue.port.IssueCommentPort.IssueCommentSavePort;
import management.sedef.issue.repository.IssueCommentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class IssueCommentAdapter implements IssueCommentReadPort, IssueCommentDeletePort, IssueCommentSavePort {

    private final IssueCommentEntityToDomainMapper issueCommentEntityToDomainMapper = IssueCommentEntityToDomainMapper.initialize();
    private final IssueCommentToEntityMapper issueCommentToEntityMapper = IssueCommentToEntityMapper.initialize();
    private final IssueCommentRepository issueCommentRepository;

    @Override
    public IssueComment findById(Long id) {
        return issueCommentRepository.findById(id)
                .map(issueCommentEntityToDomainMapper::map)
                .orElseThrow(() -> new IssueCommentNotFoundException("Issue comment not found with id: " + id));
    }

    @Override
    public List<IssueComment> findAllByIssueIdOrderByCreatedAtDesc(Long issueId) {
        return issueCommentRepository.findAllByIssueIdOrderByCreatedAtDesc(issueId)
                .stream()
                .map(issueCommentEntityToDomainMapper::map)
                .toList();
    }

    @Override
    public List<IssueComment> findAllByAuthorIdOrderByCreatedAtDesc(Long authorId) {
        return issueCommentRepository.findAllByAuthorIdOrderByCreatedAtDesc(authorId)
                .stream()
                .map(issueCommentEntityToDomainMapper::map)
                .toList();
    }

    @Override
    public List<IssueComment> findAllByParentCommentIdOrderByCreatedAtAsc(Long parentCommentId) {
        return issueCommentRepository.findAllByParentCommentIdOrderByCreatedAtAsc(parentCommentId)
                .stream()
                .map(issueCommentEntityToDomainMapper::map)
                .toList();
    }

    @Override
    public void delete(IssueComment comment) {
        IssueCommentEntity entity = issueCommentRepository.findById(comment.getId())
                .orElseThrow(() -> new IssueCommentNotFoundException("Issue comment not found with id: " + comment.getId()));

        issueCommentRepository.delete(entity);
    }

    @Override
    public IssueComment save(IssueComment issueComment) {
        IssueCommentEntity entity = issueCommentToEntityMapper.map(issueComment);
        IssueCommentEntity savedEntity = issueCommentRepository.save(entity);
        return issueCommentEntityToDomainMapper.map(savedEntity);
    }

}
