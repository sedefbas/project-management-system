package management.sedef.project.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import management.sedef.company.model.Company;
import management.sedef.company.model.Group;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class Project {

    private Long id;

    private String name;

    private String description;

    private String photo;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<Group> groups;

    private Company company;
}


