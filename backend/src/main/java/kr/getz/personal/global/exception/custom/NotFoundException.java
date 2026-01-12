package kr.getz.personal.global.exception.custom;

import kr.getz.personal.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpStatusException {

    public NotFoundException(ErrorCode errorCode) {
        super(HttpStatus.NOT_FOUND, errorCode);
    }
}
