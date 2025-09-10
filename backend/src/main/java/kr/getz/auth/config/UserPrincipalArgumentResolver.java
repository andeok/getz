package kr.getz.auth.config;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import jakarta.servlet.http.HttpServletRequest;
import kr.getz.auth.controller.cookie.CookieResolver;
import kr.getz.auth.service.AuthService;
import kr.getz.user.domain.User;

@Component
public class UserPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

	private final CookieResolver cookieResolver;
	private final AuthService authService;

	public UserPrincipalArgumentResolver(CookieResolver cookieResolver, AuthService authService) {
		this.cookieResolver = cookieResolver;
		this.authService = authService;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return User.class.isAssignableFrom(parameter.getParameterType()) &&
			parameter.hasParameterAnnotation(UserPrincipal.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();

		cookieResolver.checkLoginRequired(request);
		String token = cookieResolver.extractAccessToken(request);

		return authService.getAuthUser(token);
	}
}
