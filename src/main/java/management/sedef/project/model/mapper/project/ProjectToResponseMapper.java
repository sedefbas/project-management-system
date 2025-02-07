package management.sedef.project.model.mapper.project;

import management.sedef.company.model.Company;
import management.sedef.company.model.Group;
import management.sedef.company.model.response.CompanySummaryResponse;
import management.sedef.company.model.response.GroupResponse;
import management.sedef.project.model.Project;
import management.sedef.project.model.response.ProjectResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class ProjectToResponseMapper {

    protected CompanySummaryResponse mapCompany(Company company) {
        if (company == null) {
            return null;
        }
        CompanySummaryResponse companySummaryResponse = new CompanySummaryResponse();
        companySummaryResponse.setId(company.getId());
        companySummaryResponse.setName(company.getName());
        return companySummaryResponse;
    }

    // Group'ları manuel olarak dönüştürme
    protected List<GroupResponse> mapGroups(List<Group> groups) {
        if (groups == null) {
            return null;
        }
        return groups.stream()
                .map(group -> {
                    GroupResponse groupResponse = new GroupResponse();
                    groupResponse.setId(group.getId());
                    groupResponse.setName(group.getName());
                    return groupResponse;
                })
                .toList();
    }

    public ProjectResponse map(Project project) {
        if (project == null) {
            return null;
        }

        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setId(project.getId());
        projectResponse.setName(project.getName());
        projectResponse.setDescription(project.getDescription());
        projectResponse.setPhoto(project.getPhoto());
        projectResponse.setProjectStatus(project.getProjectStatus());
        projectResponse.setStartDate(project.getStartDate());
        projectResponse.setEndDate(project.getEndDate());
        projectResponse.setGroups(mapGroups(project.getGroups()));  // Group'ları dönüştürme
        projectResponse.setCompany(mapCompany(project.getCompany()));  // Company'yi dönüştürme

        return projectResponse;
    }
}