package management.sedef.priority.model.mapper.companyPriority;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.priority.model.CompanyPriority;
import management.sedef.priority.model.entity.CompanyPriorityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyPriorityEntityToDomainMapper extends BaseMapper<CompanyPriorityEntity, CompanyPriority> {
    static CompanyPriorityEntityToDomainMapper initialize(){
        return Mappers.getMapper(CompanyPriorityEntityToDomainMapper.class);
    }

}
