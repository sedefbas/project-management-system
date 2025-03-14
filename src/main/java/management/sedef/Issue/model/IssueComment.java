package management.sedef.issue.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import management.sedef.user.model.User;



import java.time.Instant;


@Getter
@Setter
@Builder
public class IssueComment {

    private Long id;

    private String commentText;

    private Instant createdAt;

    private Instant updatedAt;

    private User author;

    private Issue issue;

    private boolean isEdited;

    private IssueComment parentComment;

}
