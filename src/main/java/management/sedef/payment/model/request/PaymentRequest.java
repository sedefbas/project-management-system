package management.sedef.payment.model.request;

import lombok.Getter;
import lombok.Setter;
import management.sedef.payment.model.BillingInfo;
import management.sedef.payment.model.CardDetails;

@Getter
@Setter
public class PaymentRequest {
    private Long subscriptionPlanId;
    private Long companyId;
    private String token; //
    private CardDetails cardDetails;
    private BillingInfo billingInfo;
    private Long identityNumber;
}
