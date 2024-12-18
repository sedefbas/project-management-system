package management.sedef.Payment.service;

import com.iyzipay.model.*;
import com.iyzipay.request.CreatePaymentRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import management.sedef.Payment.model.request.PaymentRequest;
import management.sedef.Payment.utils.NetworkUtils;
import management.sedef.auth.exception.UserNotFoundByIdException;
import management.sedef.auth.service.TokenService;
import management.sedef.company.model.Company;
import management.sedef.company.service.CompanyService;
import management.sedef.subscriptionPlan.model.SubscriptionPlan;
import management.sedef.subscriptionPlan.service.SubscriptionPlanService;
import management.sedef.user.model.User;
import management.sedef.user.port.UserReadPort;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final TokenService tokenService;
    private final UserReadPort userReadPort;
    private final SubscriptionPlanService subscriptionPlanService;
    private final CompanyService companyService;
    private final IyzipayService iyzipayService;

    public Payment processPayment(PaymentRequest paymentRequest, HttpServletRequest request) {

        Long userId = tokenService.getUserIdFromToken(paymentRequest.getToken());

        User user = userReadPort.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException(userId));

        SubscriptionPlan subscriptionPlan = subscriptionPlanService.findById(paymentRequest.getSubscriptionPlanId());

        Company company = companyService.findById(paymentRequest.getCompanyId());

        String uniqueId = UUID.randomUUID().toString();

        // PaymentRequest verilerini dışarıdan alınacak verilerle dolduruyoruz
        CreatePaymentRequest iyzipayRequest = new CreatePaymentRequest();
        iyzipayRequest.setLocale("tr");
        iyzipayRequest.setConversationId("123456789");
        iyzipayRequest.setPrice(subscriptionPlan.getPrice());
        iyzipayRequest.setPaidPrice(subscriptionPlan.getPrice());
        iyzipayRequest.setCurrency("TRY");
        iyzipayRequest.setInstallment(1);
        iyzipayRequest.setBasketId(uniqueId);
        iyzipayRequest.setPaymentChannel("WEB");
        iyzipayRequest.setPaymentGroup("SUBSCRIPTION");


        PaymentCard cardDetails = new PaymentCard();
        cardDetails.setCardHolderName(paymentRequest.getCardDetails().getCardHolderName());
        cardDetails.setCardNumber(paymentRequest.getCardDetails().getCardNumber());
        cardDetails.setExpireMonth(paymentRequest.getCardDetails().getExpireMonth());
        cardDetails.setExpireYear(paymentRequest.getCardDetails().getExpireYear());
        cardDetails.setCvc(paymentRequest.getCardDetails().getCvc());
        iyzipayRequest.setPaymentCard(cardDetails);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Alıcı Bilgilerini dışarıdan alınacak verilerle dolduruyoruz
        Buyer buyer = new Buyer();
        buyer.setId(userId.toString());
        buyer.setName(user.getFirstName());
        buyer.setSurname(user.getLastName());
        buyer.setGsmNumber(String.valueOf(user.getPhone()));
        buyer.setEmail(user.getEmail());
        buyer.setIdentityNumber(paymentRequest.getIdentityNumber().toString());
        // Formatlı tarih değerlerini ayarla
        buyer.setLastLoginDate(user.getLastLoginDate().format(formatter)); // Örn: 2024-12-18 13:35:06
        buyer.setRegistrationDate(user.getCreatedAt().format(formatter));
        buyer.setRegistrationAddress("sokak"+company.getAddress().getStreet());
        buyer.setIp(NetworkUtils.getClientIp(request));
        buyer.setCity(company.getAddress().getCity());
        buyer.setCountry(company.getAddress().getCountry());
        buyer.setZipCode(company.getAddress().getZipCode());

        iyzipayRequest.setBuyer(buyer);

        // Fatura Bilgilerini dışarıdan alınacak verilerle dolduruyoruz
        Address billingAddress = new Address();
        billingAddress.setAddress(paymentRequest.getBillingInfo().getAddress());
        billingAddress.setContactName(user.getFirstName()+" "+user.getLastName());
        billingAddress.setCity(paymentRequest.getBillingInfo().getCity());
        billingAddress.setCountry(paymentRequest.getBillingInfo().getCountry());
        billingAddress.setZipCode(paymentRequest.getBillingInfo().getZipCode());// Fatura bilgisi dışarıdan alınacak
        iyzipayRequest.setBillingAddress(billingAddress);

        // Basket Items bilgisi dışarıdan alınacak
        BasketItem basketItem = new BasketItem();
        basketItem.setId(uniqueId);
        basketItem.setName(subscriptionPlan.getStatus().name());
        basketItem.setCategory1("Software");
        basketItem.setItemType("VIRTUAL");
        basketItem.setPrice(subscriptionPlan.getPrice());

        List<BasketItem> basketItems = new ArrayList<>();
        basketItems.add(basketItem);


        iyzipayRequest.setBasketItems(basketItems);


        Payment payment = iyzipayService.processNon3DSPayment(iyzipayRequest);

        return payment;
    }


}

