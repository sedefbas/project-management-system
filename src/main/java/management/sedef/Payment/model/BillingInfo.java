package management.sedef.Payment.model;


import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class BillingInfo {
    private String address;
    private String city;
    private String country;
    private String zipCode;
}