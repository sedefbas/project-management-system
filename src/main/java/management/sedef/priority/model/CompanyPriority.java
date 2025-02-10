package management.sedef.priority.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import management.sedef.company.model.Company;

@Getter
@Setter
@Builder
public class CompanyPriority {

    private Long id;

    private Company company;

    private Priority priority;
}
