package management.sedef.label.model.mapper.label;

import management.sedef.label.model.Label;
import management.sedef.label.model.request.LabelRequest;
import management.sedef.common.model.entity.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LabelRequestToDomainMapper extends BaseMapper<LabelRequest, Label> {
    static LabelRequestToDomainMapper initialize(){
        return Mappers.getMapper(LabelRequestToDomainMapper.class);
    }

}
