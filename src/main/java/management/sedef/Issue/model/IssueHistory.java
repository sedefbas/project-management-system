package management.sedef.issue.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import management.sedef.issue.model.entity.IssueEntity;
import management.sedef.user.model.User;
import management.sedef.user.model.entity.UserEntity;

import java.time.Instant;


@Getter
@Setter
@Builder
public class IssueHistory {

    private Long id;

    private Issue issue;

    private User modifiedBy;

    private String changeDescription;

    private Instant modifiedAt;

    private Instant expirationDate;

}
