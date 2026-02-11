package kr.getz.personal.global.auth;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.getz.personal.global.auth.dto.HttpRequestInfo;
import kr.getz.personal.global.exception.ErrorCode;
import kr.getz.personal.global.exception.ErrorResponse;
import kr.getz.personal.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	private final TokenProvider tokenProvider;
	private final ObjectMapper objectMapper;

	private static final List<HttpRequestInfo> WHITELIST = List.of(
		new HttpRequestInfo(HttpMethod.POST, "/api/auth/signup"),
		new HttpRequestInfo(HttpMethod.POST, "/api/auth/login"),
		new HttpRequestInfo(HttpMethod.GET, "/api/health"),
		new HttpRequestInfo(HttpMethod.GET, "/api/auctions/**")
	);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (token == null) {
			sendUnauthorizedResponse(response, ErrorCode.LOGIN_REQUIRED);
			return;
		}

		token = token.replaceFirst("(?i)^Bearer\\s+", "");

		try {
			String memberId = tokenProvider.decodeAccessToken(token);
			request.setAttribute("memberId", memberId);
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			sendUnauthorizedResponse(response, ErrorCode.UNAUTHORIZED);
		}
	}

	private void sendUnauthorizedResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
		ErrorResponse errorResponse = ErrorResponse.from(errorCode);

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		response.getWriter()
			.write(objectMapper.writeValueAsString(errorResponse));
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

		AntPathMatcher matcher = new AntPathMatcher();
		String url = request.getRequestURI();
		String method = request.getMethod();

		return WHITELIST.stream()
			.anyMatch(info -> matcher.match(info.path(), url) && info.method().matches(method));
	}
}
