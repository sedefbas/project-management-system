package management.sedef.company.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanySummaryResponse {
    private Long id;
    private String name;
    private String logo;
}
