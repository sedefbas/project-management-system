package management.sedef.company.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {
    private Long id;
    private String street;
    private String city;
    private String zipCode;
    private String country;
}
