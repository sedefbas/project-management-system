package management.sedef.payment.controller;
import com.iyzipay.model.Payment;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import management.sedef.payment.model.request.PaymentRequest;
import management.sedef.payment.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping("/process")
    public Payment processPayment(@RequestBody PaymentRequest paymentRequest,
                                                  HttpServletRequest request) {

            return paymentService.processPayment(paymentRequest,request);
    }
}
