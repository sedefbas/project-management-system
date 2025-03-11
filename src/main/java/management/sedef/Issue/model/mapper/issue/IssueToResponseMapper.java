package management.sedef.issue.model.mapper.issue;

import management.sedef.label.model.mapper.label.LabelToIdMapper;
import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.Issue;
import management.sedef.issue.model.response.IssueResponse;
import management.sedef.priority.model.mapper.priority.PriorityToIdMapper;
import management.sedef.project.model.mapper.project.ProjectToIdMapper;
import management.sedef.stage.model.Mapper.StageToIdMapper;
import management.sedef.user.model.mapper.UserToUserSummaryMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StageToIdMapper.class, ProjectToIdMapper.class,
        PriorityToIdMapper.class, LabelToIdMapper.class,
        UserToUserSummaryMapper.class})
public interface IssueToResponseMapper extends BaseMapper<Issue, IssueResponse> {

    @Override
    @Mapping(target = "stageId", source = "stage")
    @Mapping(target = "projectId", source = "project")
    @Mapping(target = "priorityId", source = "priority")
    @Mapping(target = "labelId", source = "label")
    @Mapping(target = "createdBy", source = "createdBy")
    IssueResponse map(Issue issue);
}
