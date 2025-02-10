package management.sedef.priority.model.mapper.companyPriority;


import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.priority.model.CompanyPriority;
import management.sedef.priority.model.Priority;
import management.sedef.priority.model.request.CompanyPriorityRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyPriorityRequestToDomainMapper extends BaseMapper<CompanyPriorityRequest, Priority> {

    static CompanyPriorityRequestToDomainMapper initialize(){
        return Mappers.getMapper(CompanyPriorityRequestToDomainMapper.class);
    }

}
