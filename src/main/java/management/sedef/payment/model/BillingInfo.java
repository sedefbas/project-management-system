package management.sedef.payment.model;


import jakarta.persistence.Embeddable;
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