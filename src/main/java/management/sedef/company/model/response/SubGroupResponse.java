package management.sedef.company.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubGroupResponse {
    private Long id;
    private String name;
    private String color;
}
