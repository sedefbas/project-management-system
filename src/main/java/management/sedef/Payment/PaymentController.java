package management.sedef.Payment;
import com.iyzipay.model.Payment;
import com.iyzipay.model.ThreedsInitialize;
import com.iyzipay.request.CreatePaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/non3ds")
    public Payment createNon3DSPayment(@RequestBody CreatePaymentRequest request) {
        return paymentService.processNon3DSPayment(request);
    }

    @PostMapping("/3ds")
    public ThreedsInitialize create3DSPayment(@RequestBody CreatePaymentRequest request) {
        return paymentService.process3DSPayment(request);
    }
}
