package management.sedef.user.model.mapper;


import management.sedef.project.model.ProjectUser;
import management.sedef.user.model.User;
import management.sedef.user.model.response.UserSummaryResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserSummaryMapper {

    // ProjectUser listesine dönüştürme işlemi
    public static List<UserSummaryResponse> mapToUserSummaryResponse(List<ProjectUser> projectUsers) {
        return projectUsers.stream()
                .map(projectUser -> {
                    User user = projectUser.getUser();  // ProjectUser'dan User'a erişim
                    return new UserSummaryResponse(
                            user.getId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getPhoto()  // Fotoğraf bilgisini de alıyoruz
                    );
                })
                .collect(Collectors.toList());  // Dönüştürülmüş listeyi döndürüyoruz
    }
}
