package management.sedef.company.model.mapper.companymapper;

import management.sedef.company.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CompanyIdToDomainMapper {

    @Named("companyIdToDomain")
    Company toDomain(Long companyId);
}

