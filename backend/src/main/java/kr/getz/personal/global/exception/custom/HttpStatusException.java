package kr.getz.personal.global.exception.custom;

import kr.getz.personal.global.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpStatusException extends RuntimeException{
    private final HttpStatus status;
    private final ErrorCode errorCode;

    public HttpStatusException(HttpStatus status, ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.status = status;
        this.errorCode = errorCode;
    }
}
