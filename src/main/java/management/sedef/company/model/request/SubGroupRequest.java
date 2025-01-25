package management.sedef.company.model.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubGroupRequest {
    private Long groupId;
    private String name;
}
