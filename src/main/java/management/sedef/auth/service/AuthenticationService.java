package management.sedef.auth.service;

import jakarta.validation.Valid;
import management.sedef.auth.model.Token;
import management.sedef.auth.model.request.LoginRequest;
import management.sedef.auth.model.request.RefreshRequest;

public interface AuthenticationService {
    Token login(LoginRequest request);

    void logout(String refreshToken);

    Token refresh(RefreshRequest refreshRequest);
}
