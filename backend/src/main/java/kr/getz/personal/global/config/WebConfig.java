package kr.getz.personal.global.config;

import java.nio.file.Watchable;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.getz.personal.global.auth.MemberAuthArgumentResolver;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class WebConfig implements WebMvcConfigurer {

	private final MemberAuthArgumentResolver resolver;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(resolver);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:3000", "https://dev.getz.kr")
			.allowedHeaders(HttpHeaders.AUTHORIZATION);
	}
}
