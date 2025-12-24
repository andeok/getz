package kr.getz.personal.global.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

    private final Key accessKey;
    private final Key refreshKey;

    private final long accessExpireTime;
    private final long refreshExpireTime;

    public TokenProvider(
        @Value("${jwt.access.secret}") String accessKey,
        @Value("${jwt.refresh.secret}") String refreshKey,
        @Value("${jwt.access.expiration}") long accessExpireTime,
        @Value("${jwt.refresh.expiration}") long refreshExpireTime) {

        this.accessKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessKey));
        this.refreshKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshKey));
        this.accessExpireTime = accessExpireTime;
        this.refreshExpireTime = refreshExpireTime;
    }

    public String createAccessToken(Long memberId, String role) {
        return createToken(memberId, role, accessExpireTime, accessKey);
    }

    public String createRefreshToken(Long memberId) {
        return createToken(memberId, null, refreshExpireTime, refreshKey);
    }

    private String createToken(Long memberId, String role, long expiration, Key key) {
        Date now = new Date();

        JwtBuilder jwtBuilder = Jwts.builder()
            .subject(String.valueOf(memberId))
            .issuedAt(now)
            .expiration(new Date(now.getTime() + expiration))
            .signWith(key);

        if (role != null) {
            jwtBuilder.claim("role", role);
        }
        return jwtBuilder.compact();
    }

    public boolean validateAccessToken(String token) {
        return validateToken(token, accessKey);
    }

    public boolean validateRefreshToken(String token) {
        return validateToken(token, refreshKey);
    }

    private boolean validateToken(String token, Key secretKey) {
        try {
            Jwts.parser()
                .verifyWith((SecretKey) secretKey)
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String decodeAccessToken(String token) {
        return decodeToken(token, accessKey);
    }

    public String decodeRefreshToken(String token) {
        return decodeToken(token, refreshKey);
    }

    private String decodeToken(String token, Key refreshKey) {
        try {
            return Jwts.parser()
                .verifyWith((SecretKey) refreshKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException(e.getMessage());
        }
    }

}
