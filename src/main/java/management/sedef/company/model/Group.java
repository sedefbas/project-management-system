package management.sedef.company.model;


import lombok.*;
import management.sedef.project.model.Project;


@Data
@Builder
@Getter
@Setter
public class Group {
    private Long id;
    private String name;
    private String color;
    private Project project;
    private Company company;
  
}
