package management.sedef.priority.model.mapper.companyPriority;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.priority.model.CompanyPriority;
import management.sedef.priority.model.entity.CompanyPriorityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyPriorityToEntityMapper extends BaseMapper<CompanyPriority, CompanyPriorityEntity> {
    static CompanyPriorityToEntityMapper initialize(){
        return Mappers.getMapper(CompanyPriorityToEntityMapper.class);
    }

}
