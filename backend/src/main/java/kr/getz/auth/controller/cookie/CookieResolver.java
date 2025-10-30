package kr.getz.auth.controller.cookie;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import kr.getz.global.exception.AuctionException;
import kr.getz.global.exception.ExceptionCode;

@Component
public class CookieResolver {

	public String extractAccessToken(HttpServletRequest request) {

		return extractToken(request, CookieProvider.ACCESS_TOKEN_NAME)
			.orElseThrow(() -> new AuctionException(ExceptionCode.AUTHENTICATION_ACCESS_TOKEN_EMPTY));
	}

	public String extractRefreshToken(HttpServletRequest request) {

		return extractToken(request, CookieProvider.REFRESH_TOKEN_NAME)
			.orElseThrow(() -> new AuctionException(ExceptionCode.AUTHENTICATION_REFRESH_TOKEN_EMPTY));
	}

	private Optional<String> extractToken(HttpServletRequest request, String cookieName) {

		if (isTokenEmpty(request)) {
			return Optional.empty();
		}

		return Arrays.stream(request.getCookies())
			.filter(cookie -> cookie.getName().equals(cookieName))
			.findAny()
			.map(Cookie::getValue);
	}

	private boolean isTokenEmpty(HttpServletRequest request) {
		return isAccessTokenEmpty(request) && isRefreshTokenEmpty(request);
	}

	private boolean isAccessTokenEmpty(HttpServletRequest request) {
		return isTokenEmpty(request, CookieProvider.ACCESS_TOKEN_NAME);
	}

	private boolean isRefreshTokenEmpty(HttpServletRequest request) {
		return isTokenEmpty(request, CookieProvider.REFRESH_TOKEN_NAME);
	}

	private boolean isTokenEmpty(HttpServletRequest request, String cookieName) {
		if (request.getCookies() == null) {
			return true;
		}

		return Arrays.stream(request.getCookies())
			.noneMatch(cookie -> cookie.getName().equals(cookieName));
	}

	public void checkLoginRequired(HttpServletRequest request) {
		if(isTokenEmpty(request)) {
			throw new AuctionException(ExceptionCode.AUTHENTICATION_ACCESS_TOKEN_EMPTY);
		}

		if(isRefreshTokenEmpty(request)) {
			throw new AuctionException(ExceptionCode.AUTHENTICATION_REFRESH_TOKEN_EMPTY);
		}
	}
}
