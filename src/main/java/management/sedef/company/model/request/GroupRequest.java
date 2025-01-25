package management.sedef.company.model.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequest {
    private Long companyId;
    private String name;
}
