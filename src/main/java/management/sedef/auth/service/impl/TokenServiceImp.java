package management.sedef.auth.service.impl;

import management.sedef.auth.config.TokenConfiguration;
import management.sedef.auth.exception.TokenNotValidException;
import management.sedef.auth.model.enums.TokenClaims;
import management.sedef.common.util.RandomUtil;
import management.sedef.common.util.validation.ListUtil;
import org.apache.commons.lang3.time.DateUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import management.sedef.auth.model.Token;
import management.sedef.auth.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenServiceImp implements TokenService {

    private final TokenConfiguration tokenConfiguration;

    @Override
    public Token generate(Claims claims) {

        final long currentTimeMillis = System.currentTimeMillis();

        final JwtBuilder tokenBuilder = this.initializeTokenBuilder(currentTimeMillis);

        final Date accessTokenExpiresAt = DateUtils.addMinutes(
                new Date(currentTimeMillis), tokenConfiguration.getTokenExpiration()
        );
        final String accessToken = tokenBuilder
                .id(RandomUtil.generateUUID())
                .expiration(accessTokenExpiresAt)
                .claims(claims)
                .compact();

        final Date refreshTokenExpiresAt = DateUtils.addDays(
                new Date(currentTimeMillis), tokenConfiguration.getRefreshExpiration()
        );
        final String refreshToken = tokenBuilder
                .id(RandomUtil.generateUUID())
                .expiration(refreshTokenExpiresAt)
                .claims(claims)
                .compact();

        return Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


    @Override
    public Token generate(Claims claims, String refreshToken) {

        final long currentTimeMillis = System.currentTimeMillis();

        final JwtBuilder tokenBuilder = this.initializeTokenBuilder(currentTimeMillis);

        final Date accessTokenExpiresAt = DateUtils.addMinutes(
                new Date(currentTimeMillis), tokenConfiguration.getTokenExpiration()
        );
        final String accessToken = tokenBuilder
                .id(RandomUtil.generateUUID())
                .expiration(accessTokenExpiresAt)
                .claims(claims)
                .compact();

        return Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private JwtBuilder initializeTokenBuilder(long currentTimeMillis) {
        return Jwts.builder()
                .header()
                .type(OAuth2AccessToken.TokenType.BEARER.getValue())
                .and()
                .issuer(tokenConfiguration.getIssuer())
                .issuedAt(new Date(currentTimeMillis))
                .signWith(tokenConfiguration.getPrivateKey());
    }


    @Override
    public void verifyAndValidate(String token) {
        try {
            final Jws<Claims> claims = Jwts.parser()
                    .verifyWith(tokenConfiguration.getPublicKey())
                    .build()
                    .parseSignedClaims(token);

            final JwsHeader header = claims.getHeader();
            if (!OAuth2AccessToken.TokenType.BEARER.getValue().equals(header.getType())) {
                throw new RequiredTypeException(token);
            }

            if (!Jwts.SIG.RS256.getId().equals(header.getAlgorithm())) {
                throw new io.jsonwebtoken.security.SignatureException(token);
            }

        } catch (MalformedJwtException | ExpiredJwtException | SignatureException | RequiredTypeException exception) {
            throw new TokenNotValidException();
        }
    }


    @Override
    public Claims getPayload(String token) {
        return Jwts.parser()
                .verifyWith(tokenConfiguration.getPublicKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = getPayload(token);
        Double userIdDouble = claims.get(TokenClaims.USER_ID.getValue(), Double.class);
        return userIdDouble != null ? userIdDouble.longValue() : null;
    }



    @Override
    public UsernamePasswordAuthenticationToken getAuthentication(String token) {

        Jws<Claims> claims = Jwts.parser()
                .verifyWith(tokenConfiguration.getPublicKey())
                .build()
                .parseSignedClaims(token);

        JwsHeader header = claims.getHeader();
        Claims payload = claims.getPayload();

        final org.springframework.security.oauth2.jwt.Jwt jwt = new org.springframework.security.oauth2.jwt.Jwt(
                token,
                payload.getIssuedAt().toInstant(),
                payload.getExpiration().toInstant(),
                Map.of(
                        TokenClaims.TYPE.getValue(), header.getType(),
                        TokenClaims.ALGORITHM.getValue(), header.getAlgorithm()
                ),
                payload
        );

        final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        final List<String> permissions = ListUtil.to(payload.get(TokenClaims.USER_PERMISSIONS.getValue()), String.class);
        permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission)));

        return UsernamePasswordAuthenticationToken.authenticated(jwt, null, authorities);
    }
}
