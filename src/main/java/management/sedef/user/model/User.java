package management.sedef.user.model;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.SuperBuilder;
import management.sedef.auth.model.Role;
import management.sedef.user.model.enums.UserStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class User  {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private int phone;
    private String photo;
    private UserStatus status;
    private Role role;
    private LocalDateTime lastLoginDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public boolean isVerified() {
        return this.status == UserStatus.VERIFIED;
    }


    public void verify() {
        this.status = UserStatus.VERIFIED;
    }

}
