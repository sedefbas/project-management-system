package management.sedef.company.model.mapper.companymapper;

import management.sedef.company.model.CompanyUser;
import management.sedef.company.model.response.CompanySummaryResponse;
import management.sedef.company.model.response.CompanyUserResponse;
import management.sedef.user.model.response.UserSummaryResponse;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyUserMapper {

    public static CompanyUserResponse mapToCompanyUserResponse(CompanyUser companyUser) {
        // Map CompanyUser to CompanyUserDto (DTO: Data Transfer Object)
        CompanyUserResponse companyUserDto = new CompanyUserResponse();

        companyUserDto.setCompany(new CompanySummaryResponse(companyUser.getCompany().getId(), companyUser.getCompany().getName()));

        companyUserDto.setUser(new UserSummaryResponse(companyUser.getUser().getId(),
                companyUser.getUser().getFirstName(),
                companyUser.getUser().getLastName()));
        return companyUserDto;
    }

//    public static List<CompanyUserResponse> mapToCompanyUserDtos(List<CompanyUser> companyUsers) {
//        return companyUsers.stream()
//                .map(CompanyUserMapper::mapToCompanyUserDto)
//                .collect(Collectors.toList());
//    }
}