package management.sedef.company.model.mapper.groupmapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Group;
import management.sedef.company.model.mapper.companymapper.CompanyToCompanySummaryResponseMapper;
import management.sedef.company.model.response.GroupResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CompanyToCompanySummaryResponseMapper.class})
public interface GroupToGroupResponseMapper extends BaseMapper<Group, GroupResponse> {

    @Mapping(target = "companySummaryResponse", source = "company")
    GroupResponse map(Group group);
}
