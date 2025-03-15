package management.sedef.issue.Service;


import management.sedef.issue.model.IssueComment;
import management.sedef.issue.model.request.IssueCommentRequest;
import management.sedef.issue.model.request.IssueCommentUpdateRequest;

import java.util.List;

public interface IssueCommentService {

    IssueComment saveComment(IssueCommentRequest request,String token);

    List<IssueComment> getCommentsByIssueId(Long issueId);

    List<IssueComment> getCommentsByAuthorId(Long authorId);

    void deleteComment(Long commentId);

    IssueComment updateComment(Long commentId, IssueCommentUpdateRequest updateRequest);  // Güncelleme metodu

    IssueComment findById(Long commentId);
}
