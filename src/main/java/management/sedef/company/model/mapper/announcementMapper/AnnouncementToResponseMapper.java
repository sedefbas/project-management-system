package management.sedef.company.model.mapper.announcementMapper;

import management.sedef.company.model.Announcement;
import management.sedef.company.model.response.AnnouncementResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface AnnouncementToResponseMapper {

    @Mapping(source = "company.id", target = "companyId")
    AnnouncementResponse map(Announcement announcement);
}
