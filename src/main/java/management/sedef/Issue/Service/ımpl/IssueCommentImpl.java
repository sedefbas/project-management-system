package management.sedef.issue.Service.ımpl;


import lombok.RequiredArgsConstructor;
import management.sedef.auth.service.TokenService;
import management.sedef.issue.Service.IssueCommentService;
import management.sedef.issue.kafka.event.EventType;
import management.sedef.issue.kafka.event.IssueCommentEvent;
import management.sedef.issue.kafka.producer.IssueCommentProducer;
import management.sedef.issue.model.IssueComment;
import management.sedef.issue.model.mapper.issueComment.IssueCommentRequestToDomainMapper;
import management.sedef.issue.model.request.IssueCommentRequest;
import management.sedef.issue.model.request.IssueCommentUpdateRequest;
import management.sedef.issue.port.IssueCommentPort.IssueCommentDeletePort;
import management.sedef.issue.port.IssueCommentPort.IssueCommentReadPort;
import management.sedef.issue.port.IssueCommentPort.IssueCommentSavePort;
import management.sedef.user.model.User;
import management.sedef.user.service.UserService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueCommentImpl implements IssueCommentService {

    private final IssueCommentRequestToDomainMapper issueCommentRequestToDomainMapper;
    private final IssueCommentReadPort readPort;
    private final IssueCommentSavePort savePort;
    private final IssueCommentDeletePort deletePort;
    private final IssueCommentProducer issueCommentProducer;
    private final UserService userService;

    @Override
    public IssueComment saveComment(IssueCommentRequest request, String token) {

        User user = userService.getUserFromToken(token);

        IssueComment issueComment = issueCommentRequestToDomainMapper.map(request);
        issueComment.setAuthor(user);
        IssueComment issueCommentSaved =  savePort.save(issueComment);

        IssueCommentEvent issueCommentEvent = IssueCommentEvent.builder()
                .issueId(issueComment.getIssue().getId())
                .eventType(EventType.ISSUE_COMMENT_CREATED)
                .modifiedAt(Instant.now())
                .commenterId(issueComment.getAuthor().getId())
                .build();

        issueCommentProducer.sendMessage(issueCommentEvent);
        return issueCommentSaved;
    }


    @Override
    public void deleteComment(Long commentId) {

        IssueComment issueComment = readPort.findById(commentId);

        IssueCommentEvent issueCommentEvent = IssueCommentEvent.builder()
                .issueId(issueComment.getIssue().getId())
                .eventType(EventType.ISSUE_COMMENT_DELETED)
                .modifiedAt(Instant.now())
                .commenterId(issueComment.getAuthor().getId())
                .build();

        issueCommentProducer.sendMessage(issueCommentEvent);

        deletePort.delete(issueComment);
    }


    @Override
    public IssueComment updateComment(Long commentId, IssueCommentUpdateRequest updateRequest) {

        IssueComment issueComment = readPort.findById(commentId);

        String newCommentText = updateRequest.getCommentText();

        issueComment.setCommentText(newCommentText);
        issueComment.setEdited(true);


        IssueCommentEvent issueCommentEvent = IssueCommentEvent.builder()
                .issueId(issueComment.getIssue().getId())
                .eventType(EventType.ISSUE_COMMENT_UPDATED)
                .modifiedAt(Instant.now())
                .commenterId(issueComment.getAuthor().getId())
                .build();

        issueCommentProducer.sendMessage(issueCommentEvent);

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
    public IssueComment findById(Long commentId) {
        return readPort.findById(commentId);
    }

}
