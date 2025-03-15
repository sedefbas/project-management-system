package management.sedef.issue.model.request;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueCommentUpdateRequest {
    private String commentText;
}
