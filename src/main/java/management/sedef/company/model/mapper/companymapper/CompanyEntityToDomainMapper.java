package management.sedef.company.model.mapper.companymapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Company;
import management.sedef.company.model.entity.CompanyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyEntityToDomainMapper extends BaseMapper<CompanyEntity, Company> {

    static CompanyEntityToDomainMapper initialize(){
        return Mappers.getMapper(CompanyEntityToDomainMapper.class);
    }
}
