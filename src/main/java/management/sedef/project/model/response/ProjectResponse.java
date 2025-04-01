package management.sedef.project.model.response;


import lombok.Getter;
import lombok.Setter;
import management.sedef.company.model.response.CompanySummaryResponse;
import management.sedef.project.model.enums.ProjectStatus;

import java.time.LocalDate;


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

}
