package management.sedef.auth.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import management.sedef.auth.exception.UserNotFoundByEmailException;
import management.sedef.auth.exception.UserPasswordNotValidException;
import management.sedef.auth.model.Identity;
import management.sedef.auth.model.Token;
import management.sedef.auth.model.enums.TokenClaims;
import management.sedef.auth.model.request.LoginRequest;
import management.sedef.auth.model.request.RefreshRequest;
import management.sedef.auth.service.AuthenticationService;
import management.sedef.auth.service.InvalidTokenService;
import management.sedef.auth.service.TokenService;
import management.sedef.user.model.User;
import management.sedef.user.port.UserReadPort;
import management.sedef.user.port.UserSavePort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserReadPort userReadPort;
    private final UserSavePort userSavePort;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final InvalidTokenService invalidTokenService;
    private final Identity identity;

//todo hesap doğrulması yapılmadan giriş yapılmamalı düzelt ?
    @Override
    public Token login(LoginRequest request) {

        User user = userReadPort.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundByEmailException(request.getEmail()));


        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UserPasswordNotValidException();
        }

        user.setLastLoginDate(LocalDateTime.now());
        userSavePort.save(user);

        final Claims claims = this.generateClaims(user);
        return tokenService.generate(claims);
    }

    private Claims generateClaims(User user) {
        final ClaimsBuilder claimsBuilder = Jwts.claims();
        claimsBuilder.add(TokenClaims.USER_ID.getValue(), user.getId());
        claimsBuilder.add(TokenClaims.USER_FIRST_NAME.getValue(), user.getFirstName());
        claimsBuilder.add(TokenClaims.USER_LAST_NAME.getValue(), user.getLastName());
        claimsBuilder.add(TokenClaims.USER_MAIL.getValue(), user.getEmail());
        claimsBuilder.add(TokenClaims.USER_ROLE.getValue(), user.getRole().getName());
        claimsBuilder.add(TokenClaims.USER_PERMISSIONS.getValue(), user.getRole().getPermissionNames());
        return claimsBuilder.build();
    }

    @Override
    public void logout(String refreshToken) {

        final String refreshTokenId = tokenService.getPayload(refreshToken).getId();
        final String tokenId = tokenService.getPayload(identity.getAccessToken()).getId();

        final List<String> invalidTokenIds = List.of(refreshTokenId, tokenId);
        invalidTokenService.saveAll(invalidTokenIds);
    }

    @Override
    public Token refresh(RefreshRequest refreshRequest) {

        final String refreshToken = refreshRequest.getRefreshToken();
        final Claims payload = tokenService.getPayload(refreshToken);

        final String email = payload.get(TokenClaims.USER_MAIL.getValue(), String.class);
        final User user = userReadPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundByEmailException(email));

        final Claims claims = this.generateClaims(user);

        return tokenService.generate(claims, refreshToken);
    }
}
