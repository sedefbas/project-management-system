package management.sedef.auth.service;
import management.sedef.auth.model.request.RegisterRequest;
import management.sedef.auth.model.request.VerifyRequest;
import org.springframework.web.multipart.MultipartFile;

public interface RegistrationService {

    void register(RegisterRequest request, MultipartFile photo);

    void verifyMail(VerifyRequest verifyRequest);
}
