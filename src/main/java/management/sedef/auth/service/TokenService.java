package management.sedef.auth.service;

import io.jsonwebtoken.Claims;
import management.sedef.auth.model.Token;
import management.sedef.project.model.claims.ProjectUserClaims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface TokenService {
    Token generate(Claims claims);

    Token generate(Claims claims, String refreshToken);

    void verifyAndValidate(String token);

    Claims getPayload(String token);

    UsernamePasswordAuthenticationToken getAuthentication(String token);

    Long getUserIdFromToken(String token);

    ProjectUserClaims parseProjectInvitationToken(String token);

}
