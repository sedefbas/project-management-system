package management.sedef.project.model.response;


import lombok.Getter;
import lombok.Setter;
import management.sedef.company.model.Company;
import management.sedef.company.model.Group;
import management.sedef.company.model.response.CompanySummaryResponse;
import management.sedef.company.model.response.GroupResponse;
import management.sedef.project.model.enums.ProjectStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProjectResponse {
    private Long id;

    private String name;

    private String description;

    private String photo;

    private ProjectStatus projectStatus;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<GroupResponse> groups;

    private CompanySummaryResponse company;
}
