package management.sedef.stage.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StageSummaryResponse {
    private Long id;
    private String name;
}
