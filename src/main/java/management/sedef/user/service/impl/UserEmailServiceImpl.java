package management.sedef.user.service.impl;

import lombok.RequiredArgsConstructor;
import management.sedef.common.model.enums.MailTemplate;
import management.sedef.common.model.request.MailSendRequest;
import management.sedef.common.service.MailService;
import management.sedef.issue.model.IssueAssignment;
import management.sedef.user.service.UserEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserEmailServiceImpl implements UserEmailService {


    @Value(value = "${application.front-end.url}")
    private String frontEndUrl;

    private final MailService mailService;

    @Override
    public void sendVerification(final String email,
                                 final Long verificationId) {

        final String baseUrl = frontEndUrl + "/auth/verify?verificationId=" + verificationId;
        System.out.println("BASE_URL: " + baseUrl);


        final Map<String, Object> parameters = Map.of(
                "BASE_URL", baseUrl
        );

        final MailSendRequest mailSendRequest = MailSendRequest.builder()
                .to(List.of(email))
                .template(MailTemplate.USER_VERIFICATION)
                .parameters(parameters)
                .build();

        mailService.send(mailSendRequest);
    }


    @Override
    public void sendProjectInvitation(String email, String token, String projectName, String fullName) {
        final String invitationLink = frontEndUrl + "/invite?token=" + token;
        System.out.println("INVITATION_LINK: " + invitationLink);

        final Map<String, Object> parameters = Map.of(
                "INVITE_PROJECT_URL", invitationLink,
                "PROJECT_NAME", projectName,
                "USERNAME", fullName
        );

        final MailSendRequest mailSendRequest = MailSendRequest.builder()
                .to(List.of(email))
                .template(MailTemplate.PROJECT_INVITATION)
                .parameters(parameters)
                .build();

        mailService.send(mailSendRequest);
    }

    @Override
    public void sendCompanyInvitation(String email, Long companyId, String companyName, String userName, String token) {
        final String invitationLink = frontEndUrl + "/invite?token=" + token;
        System.out.println("INVITATION_LINK: " + invitationLink);

        final Map<String, Object> parameters = Map.of(
                "INVITE_COMPANY_URL", invitationLink,
                "COMPANY_NAME", companyName,
                "USER_NAME",userName // Şirket adını parametre olarak ekliyoruz
        );

        final MailSendRequest mailSendRequest = MailSendRequest.builder()
                .to(List.of(email))
                .template(MailTemplate.COMPANY_INVITATION)
                .parameters(parameters)
                .build();

        mailService.send(mailSendRequest);
    }

    @Override
    public void reportIssue(IssueAssignment issueAssignment) {

        String userName = issueAssignment.getUserfullName();
        String role = issueAssignment.getRole() != null ? issueAssignment.getRole().getName() : null;
        String assignedBy = issueAssignment.getAssignedByfullName();


        final Map<String, Object> parameters = Map.of(
                "USER_NAME", userName,
                "ROLE", role,
                "ASSIGNED_BY", assignedBy,
                "ISSUE_NAME", issueAssignment.getIssue().getName()
        );

        final MailSendRequest mailSendRequest = MailSendRequest.builder()
                .to(List.of(issueAssignment.getAssignedUser().getEmail()))
                .template(MailTemplate.REPORT_MAİL)
                .parameters(parameters)
                .build();

            mailService.send(mailSendRequest);

    }



    @Override
    public void sendRegisterInvitation(String email, Long companyId, String companyName, String token
    ) {
        final String registerLink = frontEndUrl + "/register?token=" + token;
        System.out.println("REGISTER_LINK: " + registerLink);

        final Map<String, Object> parameters = Map.of(
                "REGISTER_URL", registerLink,
                "COMPANY_NAME", companyName
        );

        final MailSendRequest mailSendRequest = MailSendRequest.builder()
                .to(List.of(email))
                .template(MailTemplate.REGISTER_INVITATION)
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
