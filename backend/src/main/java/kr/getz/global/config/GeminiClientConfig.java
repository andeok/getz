package kr.getz.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import kr.getz.global.handler.GeminiExceptionHandler;

@Configuration
public class GeminiClientConfig {

	@Bean
	RestClient restClient(GeminiExceptionHandler geminiExceptionHandler) {
		return RestClient.builder()
			.defaultStatusHandler(geminiExceptionHandler)
			.build();
	}
}
