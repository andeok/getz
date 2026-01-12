package kr.getz.personal.global.exception.custom;

import kr.getz.personal.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends HttpStatusException {

    public ForbiddenException(ErrorCode errorCode) {
        super(HttpStatus.FORBIDDEN, errorCode);
    }
}
