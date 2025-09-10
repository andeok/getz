package kr.getz.global.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import kr.getz.global.exception.AuctionException;
import kr.getz.global.exception.ExceptionCode;
import kr.getz.global.exception.dto.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AuctionException.class)
	public ResponseEntity<ExceptionResponse> handleAuctionException(AuctionException ex, HttpServletRequest request) {

		ExceptionResponse response = new ExceptionResponse(
			request.getMethod(),
			request.getRequestURI(),
			ex.getClientExceptionCodeName(),
			ex.getMessage()
		);
		return ResponseEntity.status(ex.getHttpStatusCode()).body(response);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse(
			request.getMethod(),
			request.getRequestURI(),
			ExceptionCode.INTERNAL_SERVER_ERROR.getClientExceptionCode().name(),
			ExceptionCode.INTERNAL_SERVER_ERROR.getMessage()
		);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
		HttpServletRequest request) {

		FieldError fieldError = ex.getFieldError();
		String errorMessage = ExceptionCode.INVALID_PARAMETER.getMessage();

		if (fieldError != null) {
			errorMessage = fieldError.getDefaultMessage();
		}

		ExceptionResponse response = new ExceptionResponse(
			request.getMethod(),
			request.getRequestURI(),
			ExceptionCode.INVALID_PARAMETER.getClientExceptionCode().name(),
			errorMessage
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
