package management.sedef.Label.model.mapper.label;

import management.sedef.Label.model.Label;
import management.sedef.Label.model.entity.LabelEntity;
import management.sedef.common.model.entity.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface LabelToLabelEntityMapper extends BaseMapper<Label, LabelEntity> {
    static LabelToLabelEntityMapper initialize(){
        return Mappers.getMapper(LabelToLabelEntityMapper.class);
    }
}
