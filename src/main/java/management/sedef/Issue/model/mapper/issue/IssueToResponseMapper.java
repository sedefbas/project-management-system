package management.sedef.issue.model.mapper.issue;

import management.sedef.company.model.mapper.groupmapper.GroupToGroupResponseMapper;
import management.sedef.company.model.mapper.subgroupmapper.SubgroupIdToDomainMapper;
import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.Issue;
import management.sedef.issue.model.response.IssueResponse;
import management.sedef.label.model.mapper.label.LabelToLabelSummaryResponseMapper;
import management.sedef.priority.model.mapper.priority.PriorityToPrioritySummaryResponseMapper;
import management.sedef.project.model.mapper.project.ProjectToIdMapper;
import management.sedef.stage.model.Mapper.StageToStageSummaryResponseMapper;
import management.sedef.user.model.mapper.UserToUserSummaryMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        StageToStageSummaryResponseMapper.class,
        ProjectToIdMapper.class,
        PriorityToPrioritySummaryResponseMapper.class,
        LabelToLabelSummaryResponseMapper.class,
        GroupToGroupResponseMapper.class,
        SubgroupIdToDomainMapper.class,
        UserToUserSummaryMapper.class})
public interface IssueToResponseMapper extends BaseMapper<Issue, IssueResponse> {

    @Override
    @Mapping(target = "stage", source = "stage")
    @Mapping(target = "projectId", source = "project")
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "label", source = "label")
    @Mapping(target = "createdBy", source = "createdBy")
    @Mapping(target = "group", source = "group")
    @Mapping(target = "subGroup", source = "subGroup")
    IssueResponse map(Issue issue);
}
