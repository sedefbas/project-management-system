package management.sedef.user.service;

public interface UserEmailService {

    void sendVerification(String email, Long verificationId);

    void sendWelcome(String email);

}
