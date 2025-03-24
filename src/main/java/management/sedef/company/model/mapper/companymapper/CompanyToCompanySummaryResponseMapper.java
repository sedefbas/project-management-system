package management.sedef.company.model.mapper.companymapper;

import management.sedef.company.model.Company;
import management.sedef.company.model.response.CompanySummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CompanyToCompanySummaryResponseMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "logo", source = "logo")
    CompanySummaryResponse toCompanySummaryResponse(Company company);
}

