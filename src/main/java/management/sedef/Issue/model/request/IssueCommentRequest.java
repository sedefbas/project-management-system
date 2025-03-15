package management.sedef.issue.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import management.sedef.issue.model.entity.IssueEntity;
import management.sedef.user.model.entity.UserEntity;

import java.time.Instant;


@Getter
@Setter
@Builder
public class IssueCommentRequest {

    private String commentText;

    private Long issueId;

}
