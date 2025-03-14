package management.sedef.issue.port.IssueCommentPort;

import management.sedef.issue.model.IssueComment;

import java.util.List;

public interface IssueCommentReadPort {

    IssueComment findById(Long id);

    List<IssueComment> findAllByIssueIdOrderByCreatedAtDesc(Long issueId);

    List<IssueComment> findAllByAuthorIdOrderByCreatedAtDesc(Long authorId);

    List<IssueComment> findAllByParentCommentIdOrderByCreatedAtAsc(Long parentCommentId);
}
