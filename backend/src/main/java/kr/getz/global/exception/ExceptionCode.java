package kr.getz.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ExceptionCode {
	// 전체
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ClientExceptionCode.INTERNAL_SERVER_ERROR,
		"예상치 못한 서버에러가 발생했습니다"),
	INVALID_PARAMETER(HttpStatus.BAD_REQUEST, ClientExceptionCode.INVALID_PARAMETER, "잘못된 인자입니다."),

	// Option
	OPTION_INVALID(HttpStatus.BAD_REQUEST, ClientExceptionCode.CHECKLIST_ERROR, "잘못된 옵션 ID입니다."),
	OPTION_DUPLICATED(HttpStatus.BAD_REQUEST, ClientExceptionCode.CHECKLIST_ERROR, "중복된 옵션이 존재합니다."),

	// Bid
	BID_NOT_FOUND(HttpStatus.NOT_FOUND, ClientExceptionCode.BID_ERROR, "입찰이 존재하지 않습니다"),

	BID_PRICE_ERROR(HttpStatus.BAD_REQUEST, ClientExceptionCode.BID_ERROR, "입찰 금액이 잘못됐습니다."),

	BID_DUPLICATE_ERROR(HttpStatus.CONFLICT, ClientExceptionCode.BID_ERROR, "연속 입찰을 할 수 없습니다."),
	BID_TIME_ERROR(HttpStatus.BAD_REQUEST, ClientExceptionCode.BID_ERROR, "경매 에러"),

	// User
	USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, ClientExceptionCode.USER_NOT_FOUND, "유저가 존재하지 않습니다."),
	USER_INVALID_PASSWORD(HttpStatus.BAD_REQUEST, ClientExceptionCode.LOGIN_ERROR, "비밀번호가 일치하지 않습니다."),
	USER_EMAIL_ALREADY_USED(HttpStatus.CONFLICT, ClientExceptionCode.USER_EMAIL_ALREADY_USED,
		"이미 해당 이메일을 사용하는 유저가 존재합니다."),
	GUEST_USER_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, ClientExceptionCode.GUEST_USER_NOT_FOUND,
		"게스트 유저가 존재하지 않습니다."),
	GUEST_USER_UNEXPECTED_EXIST(HttpStatus.INTERNAL_SERVER_ERROR, ClientExceptionCode.GUEST_USER_UNEXPECTED_EXIST,
		"예상치 못한 게스트 유저가 존재합니다. 데이터베이스를 확인해주세요."),

	//Email
	EMAIL_INVALID_FORMAT(HttpStatus.BAD_REQUEST, ClientExceptionCode.USER_INVALID_FORMAT, "유효하지 않은 이메일 형식입니다."),

	//Password
	PASSWORD_INVALID_FORMAT(HttpStatus.BAD_REQUEST, ClientExceptionCode.USER_INVALID_FORMAT, "유효하지 않은 비밀번호 형식입니다."),
	PASSWORD_HASHING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ClientExceptionCode.PASSWORD_HASHING_ERROR,
		"비밀번호 해싱 중 오류가 발생했습니다."),

	// Answer
	ANSWER_INVALID(HttpStatus.BAD_REQUEST, ClientExceptionCode.CHECKLIST_ERROR, "답변이 유효하지 않습니다."),

	// Auction
	AUCTION_NOT_FOUND(HttpStatus.NOT_FOUND, ClientExceptionCode.AUCTION_NOT_FOUND, "경매가 존재하지 않습니다."),
	AUCTION_NOT_OWNED_BY_USER(HttpStatus.FORBIDDEN, ClientExceptionCode.UNAUTH_ERROR, "유저의 경매가 아닙니다."),

	// CustomChecklist
	CUSTOM_CHECKLIST_QUESTION_EMPTY(HttpStatus.BAD_REQUEST, ClientExceptionCode.CUSTOM_ERROR, "커스텀 질문 개수가 유효하지 않습니다."),
	CUSTOM_CHECKLIST_NOT_FOUND(HttpStatus.BAD_REQUEST, ClientExceptionCode.CUSTOM_ERROR, "커스텀 질문이 존재하지 않습니다."),

	// ChecklistShare
	CHECKLIST_SHARE_NOT_FOUND(HttpStatus.BAD_REQUEST, ClientExceptionCode.CHECKLIST_SHARE_NOT_FOUND,
		"체크리스트 공유 정보가 존재하지 않습니다."),

	// ChecklistImage
	CHECKLIST_IMAGE_INVALID_COUNT(HttpStatus.BAD_REQUEST, ClientExceptionCode.CHECKLIST_ERROR,
		"체크리스트 이미지는 최대 5개 가능합니다."),
	CHECKLIST_IMAGE_NOT_FOUND(HttpStatus.BAD_REQUEST, ClientExceptionCode.CHECKLIST_ERROR,
		"해당하는 체크리스트 이미지가 존재하지 않습니다."),
	// FloorLevel
	FLOOR_LEVEL_INVALID(HttpStatus.BAD_REQUEST, ClientExceptionCode.CHECKLIST_ERROR, "층 종류가 유효하지 않습니다."),

	// Structure
	STRUCTURE_INVALID(HttpStatus.BAD_REQUEST, ClientExceptionCode.CHECKLIST_ERROR, "방 구조가 유효하지 않습니다."),

	// Room
	ROOM_FLOOR_AND_LEVEL_INVALID(HttpStatus.BAD_REQUEST, ClientExceptionCode.CHECKLIST_ERROR,
		"방이 지상층일 경우에만 층수를 입력할 수 있습니다."),

	// OccupancyMonth
	OCCUPANCY_MONTH_INVALID(HttpStatus.BAD_REQUEST, ClientExceptionCode.CHECKLIST_ERROR,
		"입주 가능월은 1부터 12 사이 혹은 null 값만 가능합니다."),

	// OccupancyPeriod
	OCCUPANCY_PERIOD_INVALID(HttpStatus.BAD_REQUEST, ClientExceptionCode.CHECKLIST_ERROR,
		"입주 가능 기간은 초, 중, 말 혹은 null 값만 가능합니다."),

	// MaintenanceItem
	MAINTENANCE_ITEM_DUPLICATE(HttpStatus.BAD_REQUEST, ClientExceptionCode.CHECKLIST_ERROR, "중복된 관리비 항목이 존재합니다."),
	MAINTENANCE_ITEM_INVALID(HttpStatus.BAD_REQUEST, ClientExceptionCode.CHECKLIST_ERROR, "유효하지 않은 관리비 항목이 입력되었습니다."),

	// Auth
	AUTHENTICATION_ACCESS_TOKEN_EMPTY(HttpStatus.UNAUTHORIZED, ClientExceptionCode.AUTH_ACCESS_TOKEN_EMPTY,
		"액세스 토큰이 존재하지 않습니다. 액세스 토큰을 발급해주세요."),
	AUTHENTICATION_REFRESH_TOKEN_EMPTY(HttpStatus.UNAUTHORIZED, ClientExceptionCode.AUTH_TOKEN_EMPTY,
		"리프레시 토큰이 존재하지 않습니다. 다시 로그인해주세요."),
	AUTHENTICATION_TOKEN_EMPTY(HttpStatus.UNAUTHORIZED, ClientExceptionCode.AUTH_TOKEN_EMPTY, "로그인이 필요한 사용자입니다."),
	AUTHENTICATION_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, ClientExceptionCode.AUTH_TOKEN_INVALID, "토큰이 만료되었습니다."),
	AUTHENTICATION_TOKEN_INVALID(HttpStatus.UNAUTHORIZED, ClientExceptionCode.AUTH_TOKEN_INVALID, "토큰 정보가 올바르지 않습니다."),
	AUTHENTICATION_TOKEN_USER_MISMATCH(HttpStatus.UNAUTHORIZED, ClientExceptionCode.AUTH_TOKEN_USER_MISMATCH,
		"엑세스 토큰과 리프레시 토큰의 소유자가 다릅니다."),
	AUTHENTICATION_TOKEN_TYPE_MISMATCH(HttpStatus.BAD_REQUEST, ClientExceptionCode.AUTH_TOKEN_INVALID,
		"토큰 타입이 올바르지 않습니다."),
	AUTHENTICATION_PASSWORD_CODE_NOT_FOUND(HttpStatus.BAD_REQUEST, ClientExceptionCode.AUTH_PASSWORD_CODE_NOT_FOUND,
		"비밀번호 재설정 인증 코드가 일치하지 않습니다."),
	OAUTH_TOKEN_INTERNAL_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, ClientExceptionCode.OAUTH_SERVER_ERROR,
		"카카오 서버와 통신하는 과정 중 예상치 못한 예외가 발생했습니다."),
	OAUTH_REDIRECT_URI_MISMATCH(HttpStatus.BAD_REQUEST, ClientExceptionCode.OAUTH_SERVER_ERROR,
		"일치하는 Redirect URI가 존재하지 않습니다."),
	UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED, ClientExceptionCode.UNAUTH_ERROR,
		"권한이 없는 사용자입니다. 접근이 제한되었습니다."),

	// Article
	ARTICLE_NOT_FOUND(HttpStatus.BAD_REQUEST, ClientExceptionCode.ARTICLE_NOT_FOUND, "해당 아티클이 존재하지 않습니다."),

	// Station
	STATION_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, ClientExceptionCode.STATION_SERVER_ERROR, "지하철 역을 찾을 수 없습니다."),
	STATION_NAME_NOT_SAME(HttpStatus.INTERNAL_SERVER_ERROR, ClientExceptionCode.STATION_SERVER_ERROR,
		"지하철 역을 찾을 수 없습니다."),

	//Mail
	MAIL_SEND_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ClientExceptionCode.MAIL_SEND_ERROR, "메일 전송 중 오류가 발생했습니다."),

	// File
	FILE_EMPTY(HttpStatus.BAD_REQUEST, ClientExceptionCode.FILE_ERROR, "파일이 비어있습니다."),
	FILE_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ClientExceptionCode.FILE_ERROR, "파일 업로드 중 오류가 발생하였습니다."),
	FILE_NOT_FOUND(HttpStatus.BAD_REQUEST, ClientExceptionCode.FILE_ERROR, "해당 파일이 존재하지 않습니다."),

	// Image
	IMAGE_OPTIMIZE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ClientExceptionCode.IMAGE_ERROR, "이미지 최적화 중 오류가 발생하였습니다.");

	private final HttpStatus httpStatus;
	private final ClientExceptionCode clientExceptionCode;
	private final String message;

	ExceptionCode(HttpStatus httpStatus, ClientExceptionCode clientExceptionCode, String message) {
		this.httpStatus = httpStatus;
		this.clientExceptionCode = clientExceptionCode;
		this.message = message;
	}

}
