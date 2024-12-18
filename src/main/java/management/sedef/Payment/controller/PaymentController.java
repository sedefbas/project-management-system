package management.sedef.Payment.controller;
import com.iyzipay.model.Payment;
import com.iyzipay.model.ThreedsInitialize;
import com.iyzipay.request.CreatePaymentRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import management.sedef.Payment.model.request.PaymentRequest;
import management.sedef.Payment.service.IyzipayService;
import management.sedef.Payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
