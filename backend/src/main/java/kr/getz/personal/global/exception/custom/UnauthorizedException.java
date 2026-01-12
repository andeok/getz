package kr.getz.personal.global.exception.custom;

import kr.getz.personal.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends HttpStatusException {

    public UnauthorizedException(ErrorCode errorCode) {
        super(HttpStatus.UNAUTHORIZED, errorCode);
    }
}
