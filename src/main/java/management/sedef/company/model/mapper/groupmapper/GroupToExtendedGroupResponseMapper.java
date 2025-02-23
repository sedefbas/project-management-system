package management.sedef.company.model.mapper.groupmapper;

import management.sedef.company.model.Group;
import management.sedef.company.model.response.ExtendedGroupResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupToExtendedGroupResponseMapper {

    GroupToExtendedGroupResponseMapper INSTANCE = Mappers.getMapper(GroupToExtendedGroupResponseMapper.class);

    @Mapping(source = "company.id", target = "companySummaryResponse.id")
    @Mapping(source = "company.name", target = "companySummaryResponse.name")
    @Mapping(source = "company.logo", target = "companySummaryResponse.logo")
    ExtendedGroupResponse toExtendedGroupResponse(Group group);

}
