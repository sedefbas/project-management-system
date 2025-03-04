package management.sedef.auth.model.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import management.sedef.auth.model.enums.RoleName;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    @Size(min = 8, max = 255)
    @Email
    private String email;

    @NotBlank
    @Size(min = 2, max = 25)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 25)
    private String lastName;

    @NotBlank
    @Size(min = 8, max = 255)
    private String password;

    private Long phone;

    private String photo;

    private RoleName roleName;
}
