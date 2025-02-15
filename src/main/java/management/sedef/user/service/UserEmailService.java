package management.sedef.user.service;

public interface UserEmailService {

    void sendVerification(String email, Long verificationId);

    void sendRegisterInvitation(String email, Long companyId, String companyName);

    void sendWelcome(String email);

    void sendProjectInvitation(String email, String token, String projectName, String userName );

    void sendCompanyInvitation(String email, Long companyId, String companyName, String userName);

}
