package management.sedef.company.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubGroupUpdateRequest {
    @NotNull
    private String name;
}
