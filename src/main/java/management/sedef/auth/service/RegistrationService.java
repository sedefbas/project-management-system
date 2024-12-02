package management.sedef.auth.service;
import management.sedef.auth.model.request.RegisterRequest;
import management.sedef.auth.model.request.VerifyRequest;

public interface RegistrationService {

    void register(RegisterRequest request);

    void verifyMail(VerifyRequest verifyRequest);
}
