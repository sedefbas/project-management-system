package management.sedef.company.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUserDeleteRequest {
    @NotNull(message = "Şirket ID boş olamaz.")
    private Long companyId;

    @NotNull(message = "Kullanıcı ID boş olamaz.")
    private Long userId;
}
