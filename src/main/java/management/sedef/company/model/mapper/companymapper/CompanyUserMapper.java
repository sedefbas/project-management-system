package management.sedef.company.model.mapper.companymapper;

import management.sedef.company.model.CompanyUser;
import management.sedef.company.model.response.CompanySummaryResponse;
import management.sedef.company.model.response.CompanyUserResponse;
import management.sedef.user.model.response.UserSummaryResponse;


public class CompanyUserMapper {

    public static CompanyUserResponse mapToCompanyUserResponse(CompanyUser companyUser) {

        CompanyUserResponse companyUserDto = new CompanyUserResponse();

        companyUserDto.setCompany(new CompanySummaryResponse(companyUser.getCompany().getId(), companyUser.getCompany().getName(), companyUser.getCompany().getLogo()));

        companyUserDto.setUser(new UserSummaryResponse(companyUser.getUser().getId(),
                companyUser.getUser().getFirstName(),
                companyUser.getUser().getLastName(),
                companyUser.getUser().getPhoto()));  // Burada photo bilgisi alınıyor

        return companyUserDto;
    }
}