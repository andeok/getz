package kr.getz.auth.service.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import kr.getz.auth.service.TokenType;
import kr.getz.user.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

	private final JwtTokenProperties jwtTokenProperties;

	public String createAccessToken(User user) {
		long accessTokenExpirationMillis = jwtTokenProperties.getAccessTokenExpirationMillis();
		return createToken(user, accessTokenExpirationMillis, TokenType.ACCESS_TOKEN);
	}

	public String createRefreshToken(User user) {
		long refreshTokenExpirationMillis = jwtTokenProperties.getRefreshTokenExpirationMillis();
		return createToken(user, refreshTokenExpirationMillis, TokenType.REFRESH_TOKEN);
	}

	private String createToken(User user, long expirationMillis, TokenType tokenType) {
		Date now = new Date();
		Date expiredDate = new Date(now.getTime() + expirationMillis);

		return Jwts.builder()
			.subject(user.getId().toString())
			.issuedAt(now)
			.expiration(expiredDate)
			.claim(JwtTokenProperties.TOKEN_TYPE, tokenType.name())
			.signWith(jwtTokenProperties.getSecretKey())
			.compact();
	}
}
