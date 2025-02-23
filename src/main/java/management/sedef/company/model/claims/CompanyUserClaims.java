package management.sedef.company.model.claims;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompanyUserClaims {
    private String email;
    private Long companyId;
} 