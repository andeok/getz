package kr.getz.auth.service.jwt;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProperties {

	protected static final String TOKEN_TYPE = "type";

	private final String secretKey;
	private final long accessTokenExpirationMillis;
	private final long refreshTokenExpirationMillis;

	public JwtTokenProperties(
		@Value("${jwt.secret-key}") String secretKey,
		@Value("${jwt.accessToken-expiration-millis}") long accessTokenExpirationMillis,
		@Value("${jwt.refreshToken-expiration-mills}") long refreshTokenExpirationMillis) {
		this.secretKey = secretKey;
		this.accessTokenExpirationMillis = accessTokenExpirationMillis;
		this.refreshTokenExpirationMillis = refreshTokenExpirationMillis;
	}

	public long getAccessTokenExpirationMillis() {
		return accessTokenExpirationMillis;
	}

	public long getRefreshTokenExpirationMillis() {
		return refreshTokenExpirationMillis;
	}

	public SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
}
