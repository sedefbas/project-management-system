package management.sedef.company.model.mapper.companymapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Company;
import management.sedef.company.model.response.CompanyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface CompanyToResponseMapper extends BaseMapper<Company, CompanyResponse> {

    static CompanyToResponseMapper initialize(){
        return Mappers.getMapper(CompanyToResponseMapper.class);
    }

}
