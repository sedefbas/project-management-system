package management.sedef.project.model.request;

import lombok.*;
import management.sedef.auth.model.enums.RoleName;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUserUpdateRequest {
    private Long groupId;

    private Long subGroupId;

    private RoleName role;
}
