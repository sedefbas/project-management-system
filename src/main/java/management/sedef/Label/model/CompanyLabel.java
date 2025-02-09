package management.sedef.Label.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import management.sedef.company.model.Company;

@Getter
@Setter
@Builder
public class CompanyLabel {

    private Long id;
    private Company company;
    private Label label;
}
