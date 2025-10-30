package kr.getz.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.getz.auth.dto.request.LoginRequest;
import kr.getz.auth.dto.request.RegisterRequest;
import kr.getz.auth.dto.response.AuthTokenResponse;
import kr.getz.auth.service.jwt.JwtTokenProperties;
import kr.getz.auth.service.jwt.JwtTokenProvider;
import kr.getz.auth.service.jwt.JwtTokenResolver;
import kr.getz.global.exception.AuctionException;
import kr.getz.global.exception.ExceptionCode;
import kr.getz.user.domain.Email;
import kr.getz.user.domain.User;
import kr.getz.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final JwtTokenProperties jwtTokenProperties;
	private final JwtTokenProvider jwtTokenProvider;
	private final JwtTokenResolver jwtTokenResolver;

	@Transactional
	public Long register(RegisterRequest request) {
		validateEmailCheck(request.toUserEntity());
		return signUp(request.toRegisterEntity()).getId();
	}

	private User signUp(User userEntity) {
		User savedUser = userRepository.save(userEntity);
		return savedUser;
	}

	private void validateEmailCheck(User userEntity) {
		Optional<User> byEmail = userRepository.findByEmail(userEntity.getEmail());

		if (!byEmail.isEmpty()) {
			throw new AuctionException(ExceptionCode.USER_EMAIL_ALREADY_USED);
		}
	}

	public AuthTokenResponse localLogin(LoginRequest request) {
		User user = userRepository.findByEmail(new Email(request.email()))
			.orElseThrow(() -> new AuctionException(ExceptionCode.USER_NOT_FOUND));
		checkPassword(request, user);

		return createAuthToken(user);
	}

	private AuthTokenResponse createAuthToken(User user) {

		String accessToken = jwtTokenProvider.createAccessToken(user);
		String refreshToken = jwtTokenProvider.createRefreshToken(user);

		return new AuthTokenResponse(accessToken, refreshToken);
	}

	private void checkPassword(LoginRequest request, User user) {
		if (user.isDifferent(request.password())) {
			throw new AuctionException(ExceptionCode.USER_INVALID_PASSWORD);
		}
	}

	@Transactional(readOnly = true)
	public User getAuthUser(String token) {
		AuthUser authUser = jwtTokenResolver.resolveAccessToken(token);
		return userRepository.getUserById(authUser.id());
	}

	@Transactional
	public void withdraw(User user) {
		userRepository.deleteById(user.getId());
	}

	public void logout(String accessToken, String refreshToken) {
		AuthUser accessAuthUser = jwtTokenResolver.resolveAccessToken(accessToken);
		AuthUser refreshAuthUser = jwtTokenResolver.resolveRefreshToken(refreshToken);
		if (!accessAuthUser.id().equals(refreshAuthUser.id())) {
			throw new AuctionException(ExceptionCode.AUTHENTICATION_TOKEN_USER_MISMATCH);
		}
	}

	@Transactional(readOnly = true)
	public String reissueAccessToken(String refreshToken) {
		AuthUser authUser = jwtTokenResolver.resolveRefreshToken(refreshToken);
		User user = userRepository.getUserById(authUser.id());
		return jwtTokenProvider.createAccessToken(user);
	}
}
