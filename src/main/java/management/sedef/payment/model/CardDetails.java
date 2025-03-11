package management.sedef.payment.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDetails {
    private String cardHolderName;
    private String cardNumber;
    private String expireMonth;
    private String expireYear;
    private String cvc;
}
