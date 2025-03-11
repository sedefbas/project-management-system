package management.sedef.label.model.request;

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
