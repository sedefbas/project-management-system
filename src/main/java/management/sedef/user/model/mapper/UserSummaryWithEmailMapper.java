package management.sedef.user.model.mapper;

import management.sedef.company.model.CompanyUser;
import management.sedef.user.model.User;
import management.sedef.user.model.response.UserSummaryWithEmailResponse;

import java.util.List;
import java.util.stream.Collectors;

public class UserSummaryWithEmailMapper {

    public static List<UserSummaryWithEmailResponse> mapToUserSummaryWithEmailResponse(List<CompanyUser> companyUsers) {
        return companyUsers.stream()
                .map(companyUser -> {
                    User user = companyUser.getUser();
                    return new UserSummaryWithEmailResponse(
                            user.getId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getEmail() // E-posta adresini de ekliyoruz
                    );
                })
                .collect(Collectors.toList());
    }


}
