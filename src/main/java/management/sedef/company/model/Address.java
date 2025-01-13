package management.sedef.company.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class Address {

    private Long id;
    private String street;
    private String city;
    private String zipCode;
    private String country;
}
