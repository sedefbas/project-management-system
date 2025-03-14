package management.sedef.issue.model.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IssueCommentUpdateRequest {
    private String commentText;
}
