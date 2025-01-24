package management.sedef.company.model.mapper.companymapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Company;
import management.sedef.company.model.request.CompanyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyRequestToDomainMapper  extends BaseMapper<CompanyRequest, Company>  {
    static CompanyRequestToDomainMapper initialize(){
        return Mappers.getMapper(CompanyRequestToDomainMapper.class);
    }
}
