package management.sedef.auth.service;

import management.sedef.auth.model.Token;
import management.sedef.auth.model.request.LoginRequest;

public interface AuthenticationService {
    Token login(LoginRequest request);

   // Token refresh(RefreshRequest refreshRequest);

    void logout(String refreshToken);
}
