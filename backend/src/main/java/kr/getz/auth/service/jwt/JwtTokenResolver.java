package kr.getz.auth.service.jwt;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import kr.getz.auth.service.AuthUser;
import kr.getz.auth.service.TokenType;
import kr.getz.global.exception.AuctionException;
import kr.getz.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenResolver {

	private final JwtTokenProperties jwtTokenProperties;

	public AuthUser resolveAccessToken(String token) {
		return resolveTokenByType(token, TokenType.ACCESS_TOKEN);
	}

	public AuthUser resolveRefreshToken(String token) {
		return resolveTokenByType(token, TokenType.REFRESH_TOKEN);
	}

	private AuthUser resolveTokenByType(String token, TokenType tokenType) {
		try {
			Claims claims = Jwts.parser()
				.verifyWith(jwtTokenProperties.getSecretKey())
				.build()
				.parseSignedClaims(token)
				.getBody();

			validateTokenType(claims, tokenType);

			Long id = Long.valueOf(claims.getSubject());
			return AuthUser.from(id);
		} catch (ExpiredJwtException exception) {
			throw new AuctionException(ExceptionCode.AUTHENTICATION_TOKEN_EXPIRED);
		} catch (JwtException exception) {
			throw new AuctionException(ExceptionCode.AUTHENTICATION_TOKEN_INVALID);
		}

	}

	private void validateTokenType(Claims claims, TokenType tokenType) {
		String extractTokenType = claims.get(jwtTokenProperties.TOKEN_TYPE, String.class);
		if(!extractTokenType.equals(tokenType.name())) {
			throw new AuctionException(ExceptionCode.AUTHENTICATION_TOKEN_INVALID);
		}
	}

}
