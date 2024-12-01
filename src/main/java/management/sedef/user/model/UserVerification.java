package management.sedef.user.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import management.sedef.user.model.enums.UserVerificationStatus;


@Getter
@Setter
@SuperBuilder
public class UserVerification {

    private Long id;
    private User user;
    private UserVerificationStatus status;


    public void complete() {
        this.status = UserVerificationStatus.COMPLETED;
    }


    public boolean isCompleted() {
        return this.status == UserVerificationStatus.COMPLETED;
    }
}
