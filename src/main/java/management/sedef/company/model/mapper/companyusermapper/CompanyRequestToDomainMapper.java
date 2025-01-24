package management.sedef.company.model.mapper.companyusermapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.CompanyUser;
import management.sedef.company.model.request.CompanyUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyRequestToDomainMapper extends BaseMapper<CompanyUserRequest, CompanyUser> {
    static CompanyRequestToDomainMapper initialize(){
        return Mappers.getMapper(CompanyRequestToDomainMapper.class);
    }
}
