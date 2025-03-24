package management.sedef.label.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LabelSummaryResponse {
    private Long id;
    private String name;
}

