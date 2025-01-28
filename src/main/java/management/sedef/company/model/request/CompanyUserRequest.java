package management.sedef.company.model.request;


import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUserRequest {

    private String userEmail;

    private LocalDate startDate;
}
