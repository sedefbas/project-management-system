package management.sedef.issue.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import management.sedef.user.model.response.UserSummaryResponse;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueCommentResponse {

    private Long id;
    private String commentText;
    private Instant createdAt;
    private Instant  updatedAt;
    private boolean isEdited;
    private UserSummaryResponse user;

}
