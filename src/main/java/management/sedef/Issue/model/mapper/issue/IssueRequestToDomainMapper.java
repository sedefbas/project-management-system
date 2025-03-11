package management.sedef.issue.model.mapper.issue;

import management.sedef.label.model.mapper.label.LabelIdToDomainMapper;
import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.Issue;
import management.sedef.issue.model.request.IssueRequest;
import management.sedef.priority.model.mapper.priority.PriorityIdToDomainMapper;
import management.sedef.project.model.mapper.project.ProjectIdToDomainMapper;
import management.sedef.stage.model.Mapper.StageIdToDomainMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring", uses = {StageIdToDomainMapper.class, ProjectIdToDomainMapper.class, PriorityIdToDomainMapper.class, LabelIdToDomainMapper.class})
public interface IssueRequestToDomainMapper extends BaseMapper<IssueRequest, Issue> {

    @Override
    @Mapping(target = "stage", source = "stageId")
    @Mapping(target = "project", source = "projectId")
    @Mapping(target = "priority", source = "priorityId")
    @Mapping(target = "label", source = "labelId")
    Issue map(IssueRequest issueRequest);

}
