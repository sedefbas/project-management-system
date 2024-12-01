package management.sedef.auth.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import management.sedef.auth.exception.UserNotFoundByEmailException;
import management.sedef.auth.exception.UserPasswordNotValidException;
import management.sedef.auth.model.Token;
import management.sedef.auth.model.enums.TokenClaims;
import management.sedef.auth.model.request.LoginRequest;
import management.sedef.auth.service.AuthenticationService;
import management.sedef.auth.service.TokenService;
import management.sedef.user.model.User;
import management.sedef.user.port.UserReadPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserReadPort userReadPort;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    @Override
    public Token login(LoginRequest request) {

        User user = userReadPort.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundByEmailException(request.getEmail()));


        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UserPasswordNotValidException();
        }

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

    }
}
