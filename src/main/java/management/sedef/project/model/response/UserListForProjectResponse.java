package management.sedef.project.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import management.sedef.auth.model.Role;
import management.sedef.user.model.response.UserSummaryWithEmailResponse;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserListForProjectResponse {
    private UserSummaryWithEmailResponse user;
    private Role role;
}
