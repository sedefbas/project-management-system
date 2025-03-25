package management.sedef.company.model.response;


import lombok.*;
import management.sedef.project.model.response.ProjeSummaryResponse;


@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {
    private Long id;
    private String name;
    private String color;
    private CompanySummaryResponse companySummaryResponse;
    private ProjeSummaryResponse projectSummaryResponse;
}
