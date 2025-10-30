package kr.getz.global.handler;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.getz.global.exception.AuctionException;
import kr.getz.global.exception.ExceptionCode;
import kr.getz.global.exception.GeminiException;
import kr.getz.global.exception.dto.GeminiExceptionResponse;

@Component
public class GeminiExceptionHandler implements ResponseErrorHandler {

	@Override
	public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
		throw new GeminiException(getResponseBody(response));
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		try {
			return response.getStatusCode().is4xxClientError();
		} catch (IOException exception) {
			throw new AuctionException(ExceptionCode.OAUTH_TOKEN_INTERNAL_EXCEPTION);
		}
	}

	private GeminiExceptionResponse getResponseBody(ClientHttpResponse response) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();

			return objectMapper
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.readValue(response.getBody(), GeminiExceptionResponse.class);
		} catch (IOException exception) {
			throw new AuctionException(ExceptionCode.OAUTH_TOKEN_INTERNAL_EXCEPTION);
		}
	}

}
