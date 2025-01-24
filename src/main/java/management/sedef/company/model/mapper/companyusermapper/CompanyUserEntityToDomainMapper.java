package management.sedef.company.model.mapper.companyusermapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.CompanyUser;
import management.sedef.company.model.entity.CompanyUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyUserEntityToDomainMapper extends BaseMapper<CompanyUserEntity, CompanyUser> {

    static CompanyUserEntityToDomainMapper initialize(){
        return Mappers.getMapper(CompanyUserEntityToDomainMapper.class);
    }

}
