package management.sedef.auth.controller;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.auth.model.Token;
import management.sedef.auth.model.request.*;
import management.sedef.auth.model.response.TokenResponse;
import management.sedef.auth.service.AuthenticationService;
import management.sedef.auth.service.RegistrationService;
import management.sedef.common.model.entity.response.SuccessResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/register" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    SuccessResponse<Void> register(@RequestPart RegisterRequest request,
                                   @RequestPart MultipartFile photo) {
        registrationService.register(request, photo);
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

    @PostMapping("/refresh")
    SuccessResponse<TokenResponse> refresh(@RequestBody @Valid RefreshRequest refreshRequest) {
        final Token token = authenticationService.refresh(refreshRequest);
        final TokenResponse tokenResponse = TokenResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
        return SuccessResponse.success(tokenResponse);
    }


    @PostMapping("/logout")
    SuccessResponse<Void> logout(@RequestBody @Valid LogoutRequest logoutRequest) {
        authenticationService.logout(logoutRequest.getRefreshToken());
        return SuccessResponse.success();
    }

    @GetMapping("/secured")
    public String secured()
    {
        return "Secured!";
    }

}
