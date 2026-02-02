package kr.getz.personal.global.auth;

import java.util.Objects;

import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import jakarta.servlet.http.HttpServletRequest;
import kr.getz.personal.global.auth.dto.MemberAuth;
import kr.getz.personal.global.exception.ErrorCode;
import kr.getz.personal.global.exception.custom.UnauthorizedException;

@Component
public class MemberAuthArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		return parameter.getParameterType().equals(MemberAuth.class);
	}

	@Override
	public @Nullable Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {

		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		String memberId = Objects.requireNonNull(request).getAttribute("memberId").toString();

		if(memberId==null){
			throw new UnauthorizedException(ErrorCode.LOGIN_REQUIRED);
		}

		return new MemberAuth(Long.valueOf(memberId));
	}
}
