package management.sedef.help.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.help.model.Help;
import management.sedef.help.model.request.HelpRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;



@Mapper
public interface HelpRequestToDomainMapper extends BaseMapper<HelpRequest, Help> {
    static HelpRequestToDomainMapper initialize(){
        return Mappers.getMapper(HelpRequestToDomainMapper.class);
    }
}
