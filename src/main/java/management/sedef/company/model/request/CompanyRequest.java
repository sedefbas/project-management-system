package management.sedef.company.model.request;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Long phoneNumber;

    @NotNull
    private String email;

    private String website;

    private AddressRequest address;

}
