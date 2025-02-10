package management.sedef.stage.model.Mapper;


import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.priority.model.mapper.companyPriority.CompanyPriorityEntityToDomainMapper;
import management.sedef.stage.model.Stage;
import management.sedef.stage.model.entity.StageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StageEntityToDomainMapper extends BaseMapper<StageEntity, Stage> {

    static StageEntityToDomainMapper initialize(){
        return Mappers.getMapper(StageEntityToDomainMapper.class);
    }
}
