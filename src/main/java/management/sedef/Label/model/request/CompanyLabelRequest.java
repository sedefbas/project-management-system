package management.sedef.Label.model.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyLabelRequest {

    private String name;

    private String photo;
}
