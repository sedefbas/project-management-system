package management.sedef.issue.Service.ımpl;


import lombok.RequiredArgsConstructor;
import management.sedef.issue.Service.IssueCommentService;
import management.sedef.issue.model.IssueComment;
import management.sedef.issue.model.mapper.issueComment.IssueCommentRequestToDomainMapper;
import management.sedef.issue.model.request.IssueCommentRequest;
import management.sedef.issue.model.request.IssueCommentUpdateRequest;
import management.sedef.issue.port.IssueCommentPort.IssueCommentDeletePort;
import management.sedef.issue.port.IssueCommentPort.IssueCommentReadPort;
import management.sedef.issue.port.IssueCommentPort.IssueCommentSavePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueCommentImpl implements IssueCommentService {

    private final IssueCommentRequestToDomainMapper issueCommentRequestToDomainMapper;
    private final IssueCommentReadPort readPort;
    private final IssueCommentSavePort savePort;
    private final IssueCommentDeletePort deletePort;

    @Override
    public IssueComment saveComment(IssueCommentRequest request) {
        IssueComment issueComment = issueCommentRequestToDomainMapper.map(request);
        return savePort.save(issueComment);
    }

    @Override
    public List<IssueComment> getCommentsByIssueId(Long issueId) {
        return readPort.findAllByIssueIdOrderByCreatedAtDesc(issueId);
    }

    @Override
    public List<IssueComment> getCommentsByAuthorId(Long authorId) {
        return readPort.findAllByAuthorIdOrderByCreatedAtDesc(authorId);
    }

    @Override
    public void deleteComment(Long commentId) {
        IssueComment issueComment = readPort.findById(commentId);
        deletePort.delete(issueComment);
    }

    @Override
    public IssueComment updateComment(Long commentId, IssueCommentUpdateRequest updateRequest) {
        IssueComment issueComment = readPort.findById(commentId);

        issueComment.setCommentText(updateRequest.getCommentText());
        issueComment.setEdited(true);

        return savePort.save(issueComment);
    }

    @Override
    public IssueComment findById(Long commentId) {
        return readPort.findById(commentId);
    }

}
