package management.sedef.user.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdateRequest {
    private String token;
    private String firstName;
    private String lastName;
    private Long phone;
    private String photo;

}