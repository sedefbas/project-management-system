package management.sedef.project.model;

import jakarta.persistence.*;
import lombok.*;
import management.sedef.auth.model.Role;
import management.sedef.company.model.Group;
import management.sedef.company.model.SubGroup;
import management.sedef.user.model.User;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUser {

    private Long id;

    private User user;

    private Project project;

    private Group group;

    private SubGroup subGroup;

    private Role role;
}
