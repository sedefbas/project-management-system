package management.sedef.project.model.mapper.projectUser;

import lombok.RequiredArgsConstructor;
import management.sedef.project.model.ProjectUser;
import management.sedef.project.model.response.UserListForProjectResponse;
import management.sedef.user.model.User;
import management.sedef.user.model.response.UserSummaryWithEmailResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DomainToUserListForProjectResponseMapper {

    public List<UserListForProjectResponse> map(List<ProjectUser> projectUsers) {


        return projectUsers.stream()
                .map(projectUser -> {
                    if (projectUser.getUser() == null) {
                        throw new IllegalArgumentException("User bilgisi null olamaz.");
                    }

                    User user = projectUser.getUser();

                    UserSummaryWithEmailResponse userSummary = new UserSummaryWithEmailResponse(
                            user.getId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getEmail()
                    );

                    return new UserListForProjectResponse(userSummary, projectUser.getRole());
                })
                .collect(Collectors.toList());
    }
}
