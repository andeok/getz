package kr.getz.global.exception;

import kr.getz.global.exception.dto.GeminiExceptionResponse;

public class GeminiException extends RuntimeException {

	private GeminiExceptionResponse response;

	public GeminiException(GeminiExceptionResponse response) {
		this.response = response;
	}

	public GeminiExceptionResponse getResponse() {
		return response;
	}
}
