package management.sedef.label.model.mapper.label;

import management.sedef.label.model.Label;
import management.sedef.label.model.entity.LabelEntity;
import management.sedef.common.model.entity.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface LabelEntityToDomainMapper extends BaseMapper<LabelEntity, Label> {
    static LabelEntityToDomainMapper initialize(){
        return Mappers.getMapper(LabelEntityToDomainMapper.class);
    }
}
