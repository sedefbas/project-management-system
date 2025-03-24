package management.sedef.company.model.mapper.announcementMapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Announcement;
import management.sedef.company.model.mapper.companymapper.CompanyIdToDomainMapper;
import management.sedef.company.model.request.AnnouncementRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring", uses = CompanyIdToDomainMapper.class)
public interface AnnouncementRequestToDomainMapper extends BaseMapper<AnnouncementRequest, Announcement> {

    @Mapping(source = "companyId", target = "company", qualifiedByName = "companyIdToDomain")
    Announcement map(AnnouncementRequest announcementRequest);
}

