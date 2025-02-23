package management.sedef.company.model.request;


import jakarta.validation.constraints.NotNull;
import lombok.*;




@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {

    @NotNull
    private String name;

    private String logo;

    private String description;

    @NotNull
    private Long phoneNumber;

    @NotNull
    private String email;

    private String website;

    private AddressRequest address;

}
