package kr.getz.auth.controller.cookie;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import kr.getz.auth.service.jwt.JwtTokenProperties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CookieProvider {

	protected static final String ACCESS_TOKEN_NAME = "accessToken";
	protected static final String REFRESH_TOKEN_NAME = "refreshToken";

	private final JwtTokenProperties jwtTokenProperties;
	private final String domain;
	private final String sameSite;
	private final String accessTokenPath;
	private final String refreshTokenPath;

	public CookieProvider(JwtTokenProperties jwtTokenProperties,
		@Value("${cookie.domain}") String domain,
		@Value("${cookie.sameSite}") String sameSite,
		@Value("${cookie.access-token-path}") String accessTokenPath,
		@Value("${cookie.refresh-token-path}") String refreshTokenPath) {
		this.jwtTokenProperties = jwtTokenProperties;
		this.domain = domain;
		this.sameSite = sameSite;
		this.accessTokenPath = accessTokenPath;
		this.refreshTokenPath = refreshTokenPath;
	}

	public ResponseCookie createAccessTokenCookie(String token) {
		return createCookie(ACCESS_TOKEN_NAME, token, jwtTokenProperties.getAccessTokenExpirationMillis(),
			accessTokenPath);
	}

	public ResponseCookie createRefreshTokenCookie(String token) {
		return createCookie(REFRESH_TOKEN_NAME, token, jwtTokenProperties.getRefreshTokenExpirationMillis(), refreshTokenPath);
	}

	public ResponseCookie createCookie(String tokenName, String token, long expiredMillis, String path) {
		return ResponseCookie
			.from(tokenName, token)
			.domain(domain)
			.httpOnly(true)
			.secure(true)
			.sameSite(sameSite)
			.maxAge(Duration.ofMillis(expiredMillis))
			.path(path)
			.build();
	}

	public ResponseCookie deleteAccessTokenCookie() {
		return deleteCookie(ACCESS_TOKEN_NAME, accessTokenPath);
	}
	public ResponseCookie deleteRefreshTokenCookie() {
		return deleteCookie(REFRESH_TOKEN_NAME, refreshTokenPath);
	}

	private ResponseCookie deleteCookie(String refreshTokenName, String refreshTokenPath) {
		return ResponseCookie
			.from(refreshTokenName, "")
			.domain(domain)
			.httpOnly(true)
			.secure(true)
			.sameSite(sameSite)
			.maxAge(0)
			.path(refreshTokenPath)
			.build();
	}

}
