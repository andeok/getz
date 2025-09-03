package kr.getz.auth.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kr.getz.auth.config.UserPrincipal;
import kr.getz.auth.controller.cookie.CookieProvider;
import kr.getz.auth.controller.cookie.CookieResolver;
import kr.getz.auth.dto.request.LoginTokenRequest;
import kr.getz.auth.dto.request.LoginRequest;
import kr.getz.auth.dto.request.RegisterRequest;
import kr.getz.auth.dto.response.AuthTokenResponse;
import kr.getz.auth.dto.response.LoginInfomationResponse;
import kr.getz.auth.service.AuthService;
import kr.getz.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	private final CookieProvider cookieProvider;
	private final CookieResolver cookieResolver;

	@PostMapping("/oauth2/login/{oauth2Type}")
	public ResponseEntity<LoginInfomationResponse> login(@PathVariable String oauth2Type,
		@RequestBody @Valid LoginTokenRequest request) {

		// TODO : 우선 로컬 회원가입 먼저.
		log.info("login {} {}", oauth2Type, request);
		return null;
	}

	@PostMapping("/v1/local-auth/register")
	public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest request) {

		Long register = authService.register(request);

		return ResponseEntity.created(URI.create("/v1/local-auth/register/" + register)).build();
	}

	@PostMapping("/v1/local-auth/login")
	public ResponseEntity<LoginInfomationResponse> localLogin(@RequestBody @Valid LoginRequest request) {

		AuthTokenResponse authTokenResponse = authService.localLogin(request);

		ResponseCookie accessTokenCookie = cookieProvider.createAccessTokenCookie(authTokenResponse.accessToken());
		ResponseCookie refreshTokenCookie = cookieProvider.createRefreshTokenCookie(authTokenResponse.refreshToken());

		return ResponseEntity.ok()
			.header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
			.header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
			.build();
	}

	@DeleteMapping("/v1/withdraw")
	public ResponseEntity<Void> withdraw(@UserPrincipal User user, HttpServletRequest request) {
		String accessToken = cookieResolver.extractAccessToken(request);
		String refreshToken = cookieResolver.extractRefreshToken(request);

		authService.logout(accessToken, refreshToken);

		ResponseCookie deletedAccessToken = cookieProvider.deleteAccessTokenCookie();
		ResponseCookie deletedRefreshToken = cookieProvider.deleteRefreshTokenCookie();

		authService.withdraw(user);

		return ResponseEntity.noContent()
			.header(HttpHeaders.SET_COOKIE, deletedAccessToken.toString())
			.header(HttpHeaders.SET_COOKIE, deletedRefreshToken.toString())
			.build();
	}

	@PostMapping("/v1/logout")
	public ResponseEntity<Void> logout(@UserPrincipal User user, HttpServletRequest request) {
		String accessToken = cookieResolver.extractAccessToken(request);
		String refreshToken = cookieResolver.extractRefreshToken(request);

		authService.logout(accessToken, refreshToken);

		ResponseCookie deletedAccessTokenCookie = cookieProvider.deleteAccessTokenCookie();
		ResponseCookie deletedRefreshTokenCookie = cookieProvider.deleteRefreshTokenCookie();

		return ResponseEntity.noContent()
			.header(HttpHeaders.SET_COOKIE, deletedAccessTokenCookie.toString())
			.header(HttpHeaders.SET_COOKIE, deletedRefreshTokenCookie.toString())
			.build();
	}

	@PostMapping("/v1/reissue")
	public ResponseEntity<Void> reissue(HttpServletRequest request) {
		cookieResolver.checkLoginRequired(request);

		String refreshToken = cookieResolver.extractRefreshToken(request);
		String accessToken = authService.reissueAccessToken(refreshToken);

		ResponseCookie accessTokenCookie = cookieProvider.createAccessTokenCookie(accessToken);
		return ResponseEntity.ok()
			.header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
			.build();
	}

}
