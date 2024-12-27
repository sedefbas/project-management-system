package management.sedef.user.service.impl;

import lombok.RequiredArgsConstructor;
import management.sedef.common.model.enums.MailTemplate;
import management.sedef.common.model.request.MailSendRequest;
import management.sedef.common.service.MailService;
import management.sedef.user.service.UserEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserEmailServiceImpl implements UserEmailService {

    @Value("${application.front-end.url}")
    private String frontEndUrl;

    private final MailService mailService;

    @Override
    public void sendVerification(final String email,
                                 final Long verificationId) {

        final String baseUrl = frontEndUrl + "/auth/verify?verificationId=" + verificationId;
        System.out.println("BASE_URL: " + baseUrl);


        final Map<String, Object> parameters = Map.of(
                "BASE_URL", frontEndUrl + "/auth/verify?verificationId=" + verificationId
        );

        final MailSendRequest mailSendRequest = MailSendRequest.builder()
                .to(List.of(email))
                .template(MailTemplate.USER_VERIFICATION)
                .parameters(parameters)
                .build();

        mailService.send(mailSendRequest);
    }

    @Override
    public void sendWelcome(final String email) {

        final MailSendRequest mailSendRequest = MailSendRequest.builder()
                .to(List.of(email))
                .template(MailTemplate.USER_WELCOME)
                .build();

        mailService.send(mailSendRequest);
    }
}
