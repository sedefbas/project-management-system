package management.sedef.issue.model.mapper.issue;

import management.sedef.issue.model.Issue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface IssueIdToDomainMapper {
    @Mapping(target = "id", source = "id")
    Issue toDomain(Long id);
}
