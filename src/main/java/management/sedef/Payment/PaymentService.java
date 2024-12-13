package management.sedef.Payment;

import com.iyzipay.Options;
import com.iyzipay.model.Payment;
import com.iyzipay.model.ThreedsInitialize;
import com.iyzipay.request.CreatePaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final Options options;

    @Autowired
    public PaymentService(Options options) {
        this.options = options;
    }

    public Payment processNon3DSPayment(CreatePaymentRequest request) {
        return Payment.create(request, options);
    }

    public ThreedsInitialize process3DSPayment(CreatePaymentRequest request) {
        return ThreedsInitialize.create(request, options);
    }
}
