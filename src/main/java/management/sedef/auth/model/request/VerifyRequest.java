package management.sedef.auth.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor //
public class VerifyRequest {

    @NotNull
    private Long verificationId;

}
