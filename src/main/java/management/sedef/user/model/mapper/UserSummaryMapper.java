package management.sedef.user.model.mapper;

import management.sedef.company.model.CompanyUser;
import management.sedef.user.model.User;
import management.sedef.user.model.entity.UserEntity;
import management.sedef.user.model.response.UserSummaryResponse;

import java.util.List;
import java.util.stream.Collectors;

public class UserSummaryMapper {

    public static List<UserSummaryResponse> mapToUserSummaryResponse(List<CompanyUser> companyUsers) {
        return companyUsers.stream()
                .map(companyUser -> {
                    User user = companyUser.getUser();
                    return new UserSummaryResponse(user.getId(), user.getFirstName(), user.getLastName());
                })
                .collect(Collectors.toList());
    }
}
