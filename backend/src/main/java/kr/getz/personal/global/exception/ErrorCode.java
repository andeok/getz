package kr.getz.personal.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

	INVALID_INPUT_VALUE("입력값이 올바르지 않습니다."),
	BAD_REQUEST("올바르지 않은 요청입니다."),

	// 400 Bad Request
	DUPLICATE_EMAIL("중복 된 이메일입니다."),
	LOWER_THAN_CURRENT_PRICE("입찰 금액이 현재 가격보다 낮거나 같습니다."),

	// 401 Unauthorized
	UNAUTHORIZED("토큰 기반 인증에 실패했습니다."),
	ID_TOKEN_NOT_VALID("유효하지 않은 id token입니다."),
	ACCESS_TOKEN_EXPIRED("access token이 만료됐습니다."),
	ACCESS_TOKEN_SIGNATURE_INVALID("access token이 위조됐습니다."),
	REFRESH_TOKEN_EXPIRED("refresh token이 만료됐습니다."),
	REFRESH_TOKEN_NOT_FOUND("refresh token을 찾을 수 없습니다."),
	REFRESH_TOKEN_INVALID("유효하지 않은 refresh token입니다."),
	REFRESH_TOKEN_SIGNATURE_INVALID("refresh token이 위조됐습니다."),
	ID_PASSWORD_NOT_MATCH("이메일 또는 비밀번호가 올바르지 않습니다."),
	LOGIN_REQUIRED("로그인이 필요합니다."),

	// 403 Forbidden
	FORBIDDEN("접근 권한이 없습니다."),

	// 404 Not Found
	NOT_FOUND("요청 정보를 찾을 수 없습니다."),
	MEMBER_NOT_FOUND("회원을 찾을 수 없습니다."),

	// 409 Conflict
	EMAIL_CONFLICT("중복 된 이메일입니다."),

	// 500 Internal Server Error
	INTERNAL_SERVER_ERROR("서버에서 예기치 못한 에러가 발생했습니다."),

	AUCTION_NOT_PROCEEDING("경매가 진행 중이 아닙니다."),
	CANNOT_BID_OWN_AUCTION("본인의 경매에는 입찰할 수 없습니다."),
	LOCK_FAILURE("데이터베이스 락 획득에 실패했습니다.");

	private final String message;

	ErrorCode(String message) {
		this.message = message;
	}

}
