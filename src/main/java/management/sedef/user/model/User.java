package management.sedef.user.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import management.sedef.auth.model.Role;

@Getter
@Setter
@SuperBuilder
public class User {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private int phone;
    private String photo;
    private Role role;

}
