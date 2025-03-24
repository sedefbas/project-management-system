package management.sedef.company.model.mapper.announcementMapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Announcement;
import management.sedef.company.model.entity.AnnouncementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnnouncemenEntityToDomainMapper extends BaseMapper<AnnouncementEntity, Announcement> {
    static AnnouncemenEntityToDomainMapper initialize(){
        return Mappers.getMapper(AnnouncemenEntityToDomainMapper.class);
    }

}
