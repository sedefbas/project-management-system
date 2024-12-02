package management.sedef.auth.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.auth.model.Token;
import management.sedef.auth.model.request.LoginRequest;
import management.sedef.auth.model.request.RegisterRequest;
import management.sedef.auth.model.request.VerifyRequest;
import management.sedef.auth.model.response.TokenResponse;
import management.sedef.auth.service.AuthenticationService;
import management.sedef.auth.service.RegistrationService;
import management.sedef.common.model.entity.response.SuccessResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    SuccessResponse<Void> register(@RequestBody RegisterRequest request) {
        registrationService.register(request);
        return SuccessResponse.success();
    }

    @PostMapping("/verify-mail")
    SuccessResponse<Void> verifyMail(@RequestBody VerifyRequest verifyRequest) {
        registrationService.verifyMail(verifyRequest);
        return SuccessResponse.success();
    }

    @PostMapping("/login")
    SuccessResponse<TokenResponse> login(@RequestBody LoginRequest request) {
        final Token token = authenticationService.login(request);
        final TokenResponse tokenResponse = TokenResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
        return SuccessResponse.success(tokenResponse);
    }

}
