package kr.getz.personal.global.exception.custom;

import kr.getz.personal.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpStatusException{

    public BadRequestException(ErrorCode errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
