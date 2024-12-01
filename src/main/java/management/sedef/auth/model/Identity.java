package management.sedef.auth.model;

import management.sedef.auth.model.enums.RoleName;
import management.sedef.auth.model.enums.TokenClaims;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Set;

public class Identity {
    public String getUserId() {
        return this.getJwt().getClaim(TokenClaims.USER_ID.getValue());
    }

    public boolean isAdmin() {
        final RoleName roleName = RoleName.valueOf(
                this.getJwt().getClaim(TokenClaims.USER_ROLE.getValue())
        );
        return RoleName.ADMIN == roleName;
    }

    public boolean hasPermission(String requiredPermission) {
        Set<String> permissions = this.getJwt().getClaim(TokenClaims.USER_PERMISSIONS.getValue());
        return permissions.contains(requiredPermission);
    }

    public String getAccessToken() {
        return this.getJwt().getTokenValue();
    }

    private Jwt getJwt() {
        return (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
