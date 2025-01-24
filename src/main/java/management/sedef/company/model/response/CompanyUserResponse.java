package management.sedef.company.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import management.sedef.user.model.response.UserSummaryResponse;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUserResponse {
    private CompanySummaryResponse company;
    private UserSummaryResponse user;
}
