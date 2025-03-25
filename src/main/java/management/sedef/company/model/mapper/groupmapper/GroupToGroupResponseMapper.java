package management.sedef.company.model.mapper.groupmapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Group;
import management.sedef.company.model.mapper.companymapper.CompanyToCompanySummaryResponseMapper;
import management.sedef.company.model.response.GroupResponse;
import management.sedef.project.model.mapper.project.ProjeToProjeSummaryResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring", uses = {CompanyToCompanySummaryResponseMapper.class, ProjeToProjeSummaryResponseMapper.class})
public interface GroupToGroupResponseMapper extends BaseMapper<Group, GroupResponse> {

    @Mapping(target = "companySummaryResponse", source = "company")
    @Mapping(target = "projectSummaryResponse", source = "project")
    GroupResponse map(Group group);
}
